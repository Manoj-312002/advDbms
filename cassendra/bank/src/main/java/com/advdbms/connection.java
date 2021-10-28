package com.advdbms;
import java.util.Set;
import java.util.UUID;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.type.codec.TypeCodecs;
import com.datastax.oss.driver.api.core.type.reflect.GenericType;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.*;


public class connection {
    
    CqlSession session;
    connection() throws Exception {
        session = CqlSession.builder()
        .addTypeCodecs(TypeCodecs.ZONED_TIMESTAMP_UTC)
        .withKeyspace( CqlIdentifier.fromCql("bank") )
        .build();
        System.out.println("Session created");

    }

    public void createKeyspace( String name , int replication ){
        String q = 
        "CREATE KEYSPACE IF NOT EXISTS " + name +  " WITH replication "
         + "= {'class':'SimpleStrategy', 'replication_factor': " + replication +  "};";
        session.execute(q);
    }

    public void outKeyspaces(){
        ResultSet rs = session.execute("select * from system_schema.keyspaces;");  
        rs.all()
        .stream()
        .map( x -> x.getString(0) )
        .forEach( System.out::println);
    }

    public String insertName(String name , Set<String> ph , float balance ){
        var uuid = UUID.randomUUID();
        SimpleStatement x = insertInto( "bank" , "name_by_id" )
        .value("name", literal(name) )
        .value("id", literal(uuid) )
        .value( "ph" , literal(ph) )
        .value("balance" , literal(balance) ).build();
        
        session.execute(x);
        return uuid.toString();
    }

    public String insertPin( String uid , int cvv , int pin ){
        var uuid = UUID.randomUUID();
        SimpleStatement x = insertInto( "bank" , "pin_by_id" )
        .value("id", literal( UUID.fromString(uid) ) )
        .value("swipe_id", literal(uuid) )
        .value( "cvv" , literal(cvv) )
        .value("pin" , literal(pin) ).build();
        session.execute(x);

        return uuid.toString();
    }

    public boolean verifyPin( String uid , String swipe_id , int cvv , int pin ){
        String q = "SELECT cvv , pin FROM pin_by_id WHERE " +
        "id = " + uid + 
        "AND swipe_id = " + swipe_id + ";";
        Row r =  session.execute(q).one();
        if( r.getInt("cvv") == cvv && r.getInt("pin") == pin ) return true;
        else return false;
    }

    public boolean transact( String frId , String toId ,  float amt ){
        String q = "SELECT name , balance FROM name_by_id WHERE id = " + frId +  ";";
        Row r = session.execute(q).one();
        
        String n1 = r.getString("name");
        Float b1 = r.getFloat("balance");

        q = "SELECT name , balance FROM name_by_id WHERE id = " + toId +  ";";
        r = session.execute(q).one();
        String n2 = r.getString("name");
        Float b2 = r.getFloat("balance");
        b1 += amt;
        b2 -= amt;
        if( b1 <= 0 ) return false;

        q = 
        "BEGIN BATCH " + 
            "UPDATE name_by_id SET balance = " + b1 + " WHERE id = " + frId + " AND name = '" + n1  + "' ;" +
            "UPDATE name_by_id SET balance = " + b2 + " WHERE id = " + toId + " AND name = '" + n2  + "' ;" + 
            "INSERT INTO transaction_by_id( id , time , amt ) VALUES ( " +
                frId  + ", toTimestamp(now()) , " + amt + ") ;" +
            "APPLY BATCH;" ;

        session.execute(q);
        return true;
    }



    public void transactions( String uid ){
        String q = "SELECT * from transaction_by_id WHERE id = " + uid + " ORDER BY time ;";
        session.execute(q)
        .all()
        .stream()
        .forEach(x -> {
            System.out.println( x.get("time" , GenericType.ZONED_DATE_TIME ) + " : " + x.getFloat("amt") );
        });
    }

    public void remove( String uid , String swipe_id ){
        String q = "DELETE FROM name_by_id WHERE id = " + uid + ";";
        session.execute(q);
        q = "DELETE FROM pin_by_id WHERE id = " + uid + " AND swipe_id = " + swipe_id + ";" ;
        session.execute(q);
    }
    
    
    public Set<String> getPh( String uid ){
        String q = "SELECT ph FROM name_by_id WHERE id = " + uid + " ; ";
        Set<String> s = session.execute(q).one().getSet("ph" , String.class );
        return s;
    }
}

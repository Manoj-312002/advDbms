package com.advdbms2;
import com.mongodb.client.model.*;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import java.util.Arrays;
import java.util.List;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.*;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class connection {

    public static void main( String[] args ) {
        try( MongoClient cl = MongoClients.create("mongodb://localhost:27017") ){
            MongoDatabase db = cl.getDatabase("movies");
            MongoCollection<Document> clt = db.getCollection("list");
            
            Bson projectionFields = Projections.fields(
                Projections.include("title" , "likes"),
                Projections.excludeId()
            );
            
            Document doc = clt.find( or( eq("title", "Movie-2") , lt("likes" , 0) ) )
            .projection(projectionFields)
            .sort( Sorts.ascending("likes") )
            .first();
            System.out.println(doc.toJson());

            MongoCursor<Document> cr = clt.find( or( eq("title", "Movie-2") , gt("likes" , 0) ) )
            .projection(projectionFields)
            .sort( Sorts.ascending("likes") )
            .iterator();
            
            while( cr.hasNext() ){
                System.out.println(cr.next().toJson());
            }

            InsertOneResult rs = clt.insertOne( 
                new Document()
                .append("_id", new ObjectId())
                .append("title",  "Movie-1")
                .append("tags", Arrays.asList("Comedy" , "Documentary" ))
            );
            System.out.println(rs.getInsertedId());
            
            // clt.insesrtMany -> pass array of documents 

            // update

            Document qr = new Document().append("title", "Movie-1");
            Bson updates = Updates.combine(
                Updates.inc("likes", 1),
                Updates.set("rating", 4.8)
            );
            UpdateOptions opts = new UpdateOptions().upsert(true);

            UpdateResult Urs = clt.updateOne(qr, updates , opts);
            System.out.println( Urs.getUpsertedId() );


            Document rplDoc = new Document()
            .append("title", "Movie-5");
            ReplaceOptions Ropt = new ReplaceOptions().upsert(true);
            clt.replaceOne(qr , rplDoc , Ropt);

            clt.deleteOne(qr);

            BulkWriteResult Brs = clt.bulkWrite(
                Arrays.asList(
                    new InsertOneModel<>( new Document("title" , "Movie-6") ),
                    new UpdateOneModel<>( 
                            new Document("title" , "Movie-6") ,
                            new Document( "$set" , new Document("ratings" , 5) ) 
                        )
                )
            );
            System.out.println(Brs.getInsertedCount());

            
            List<Bson> pipeline = Arrays.asList( 
                Aggregates.match( lt("fullDocument.runtime" , 3)  ),
                Aggregates.match( in( "operationType" , Arrays.asList("insert" , "update") ) )
            );
            ChangeStreamIterable<Document> cs = db.watch(pipeline)
            .fullDocument(FullDocument.UPDATE_LOOKUP); // if not only modified field shows up on update operations
            
            cs.forEach( e -> System.out.println( e ) );
            clt.distinct("title", String.class );

        }
    }
}

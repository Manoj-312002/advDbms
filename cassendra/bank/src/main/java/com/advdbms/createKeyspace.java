package com.advdbms;

public class createKeyspace {
    public static void main(String[] args) throws Exception {
        connection db = new connection();
        db.createKeyspace("some_keyspace", 1);
        db.outKeyspaces();
        
        db.session.execute("DROP INDEX IF EXISTS aemail");
        // db.session.execute("ALTER TABLE name_by_id DROP email ");
        db.session.execute("TRUNCATE name_by_id");
        db.session.execute("TRUNCATE pin_by_id");
        db.session.execute("TRUNCATE transaction_by_id");
    }
}

CREATE KEYSPACE bank WITH replication = {
    'class' : 'SimpleStrategy' ,
    'replication_factor' : 4
} AND durable_writes = true;

ALTER KEYSPACE bank WITH replication = {
    'class' : 'SimpleStrategy', 
    'replication_factor' : 1
};

DESC KEYSPACE bank;

DROP KEYSPACE bank;

CREATE TABLE name_by_id ( 
    id uuid , 
    name text , 
    ph set<text> , 
    balance float , 
    PRIMARY KEY ( id , name )
)

CREATE TABLE pin_by_id ( 
    id uuid ,
    swipe_id uuid ,
    cvv int ,
    pin int ,
    PRIMARY KEY( ( id , swipe_id) , cvv )
)

CREATE TABLE transaction_by_id( 
    id uuid , 
    time timestamp ,
    amt float , 
    PRIMARY KEY( id , time )
)
WITH CLUSTERING ORDER BY ( time DESC )


INSERT INTO name_by_id ( id , name , ph , balance  ) VALUES (
    uuid() ,
    'user-1',
    { '99909304932' },
    1000.12
)
INSERT INTO name_by_id ( id , name , ph , balance  ) VALUES (
    uuid() ,
    'user-2',
    { '99909304342' },
    100000.12
)

INSERT INTO pin_by_id ( id , swipe_id , cvv , pin  ) VALUES (
    e2c7ca87-0319-47bd-be57-7080b043e57a ,
    uuid() , 
    123,
    3255
)

INSERT INTO pin_by_id ( id , swipe_id , cvv , pin  ) VALUES (
    24d18ce3-4960-4869-a4e8-11550f1e4e37  ,
    uuid() , 
    432 ,
    1234
)


SELECT * FROM name_by_id WHERE id = e2c7ca87-0319-47bd-be57-7080b043e57a;

SELECT * FROM pin_by_id WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND swipe_id = aeca1914-657a-40f7-96e2-cddff4c495e1 ;

SELECT * FROM pin_by_id WHERE cvv=123 ALLOW FILTERING; 

ALTER TABLE name_by_id ADD email text;
ALTER TABLE name_by_id ALTER balance TYPE counter;

UPDATE name_by_id SET email='user-1@mail.com' WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND name='user-2';

BEGIN BATCH 
    UPDATE name_by_id SET balance = 9000  WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND name='user-2';
    UPDATE name_by_id SET balance = 2000.12 WHERE id = e2c7ca87-0319-47bd-be57-7080b043e57a AND name='user-1';
    INSERT INTO transaction_by_id( id , time , amt  ) VALUES (
        24d18ce3-4960-4869-a4e8-11550f1e4e37 ,
        toTimestamp(now()), 
        -100
    );
APPLY BATCH;

UPDATE name_by_id SET ph=ph+{'9023434342'} WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND name='user-2';

UPDATE name_by_id SET ph=ph-{'9023434342'} WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND name='user-2';

DROP TABLE transaction_by_id;

CREATE INDEX aemail ON name_by_id( email );
SELECT * FROM name_by_id WHERE email='user-1@mail.com' ALLOW FILTERING;  


DELETE email FROM name_by_id WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND name='user-1'

DELETE FROM name_by_id WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND name='user-1' IF balance < 3000

INSERT INTO name_by_id ( id , name , ph , balance  ) VALUES (
    24d18ce3-4960-4869-a4e8-11550f1e4e37 ,
    'user-2',
    { '99909304342' },
    100000.12
)


INSERT INTO transaction_by_id( id , time , amt  ) VALUES (
    24d18ce3-4960-4869-a4e8-11550f1e4e37 ,
    toTimestamp(now()), 
    100
);

INSERT INTO transaction_by_id( id , time , amt  ) VALUES (
    24d18ce3-4960-4869-a4e8-11550f1e4e37 ,
    toTimestamp(now()), 
    300
);

INSERT INTO transaction_by_id( id , time , amt  ) VALUES (
    24d18ce3-4960-4869-a4e8-11550f1e4e37 ,
    toTimestamp(now()), 
    -400
);

SELECT min( balance ) as max_balance , min(balance ) from name_by_id;


SELECT max( amt ) as largest_transaction , min( amt ) FROM transaction_by_id WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37;


SELECT avg( amt ) FROM transaction_by_id WHERE id = 24d18ce3-4960-4869-a4e8-11550f1e4e37 AND time > toTimestamp(now()) - 1d;




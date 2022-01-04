connect remote:localhost root dbpass
connect remote:localhost/online_shop root dbpass

list databases;

create database remote:localhost/online_shop root dbpass


create class person extends V;
create property person.name string;
create property person.email string;
create property person.city string;
create property person.address string;

create class item extends V;
create property item.name string;
create property item.price float;
create property item.tot_qty integer;
create property item.category string;

create class supplier extends V;
create property supplier.name string;
create property supplier.city string;

create class warehouse extends V;
create property warehouse.city string;






create class has_ordered extends E;
create property has_ordered.city string;
create property has_ordered.time datetime;

create class supplied_by extends E;
create class item_isin extends E;

create vertex person set name="user1" , email="1.@gmail.com" , address="add1" , city="l1";
create vertex person set name="user2" , email="2.@gmail.com" , address="add2" , city="l2";
create vertex person set name="user3" , email="3.@gmail.com" , address="add3" , city="l2";
create vertex person set name="user4" , email="4.@gmail.com" , address="add4" , city="l3";
create vertex person set name="user5" , email="5.@gmail.com" , address="add5" , city="l4";



create vertex item set name="item1" , price=543 , tot_qty=10, category="cat1";
create vertex item set name="item2" , price=212 , tot_qty=2 , category="cat1";
create vertex item set name="item3" , price=432 , tot_qty=32 , category="cat2";
create vertex item set name="item4" , price=321 , tot_qty=100 , category="cat2";
create vertex item set name="item5" , price=6544 , tot_qty=1010, category="cat3";
create vertex item set name="item6" , price=3233, tot_qty=32, category="cat3";
create vertex item set name="item7" , price=4333, tot_qty=72 , category="cat3";
create vertex item set name="item8" , price=5555 , tot_qty=61 , category="cat3";
create vertex item set name="item9" , price=2333, tot_qty=92 , category="cat3";
create vertex item set name="item10" , price=3233 , tot_qty=34 , category="cat3";
create vertex item set name="item11" , price=543 , tot_qty=43 , category="cat4";
create vertex item set name="item12" , price=543 , tot_qty=32 , category="cat4";


create vertex supplier set name="sup1" , city="l1";
create vertex supplier set name="sup2" , city="l2";
create vertex supplier set name="sup3" , city="l2";
create vertex supplier set name="sup4" , city="l3";


create vertex warehouse set city="l2";
create vertex warehouse set city="l1";


create edge has_ordered from #22:3 TO #27:1 set qty=1 , time=DATE();


update item set tot_qty=20 where name="item1";
delete vertex warehouse where city = "l3";

select * from item where category="cat3"

select avg(price) from item;
select min(tot_qty) from item;

select * from item where price > 1000;

SELECT 
  @rid as id, 
  Name, 
  Surname, 
  both('HasFriend').size() AS FriendsNumber 
FROM `Profiles` 
ORDER BY FriendsNumber DESC 
LIMIT 3
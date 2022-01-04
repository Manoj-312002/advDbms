select * from rating , apps , users where appId = apps.id and userId = users.id;

select * from users;
select * from rating;
select * from apps;

select userId , appId , avg(Sentiment_Polarity) FROM rating GROUP BY userId , appId;

select state, userId , appId , avg(Sentiment_Polarity) FROM rating , users WHERE users.id = rating.userId GROUP BY state, userId , appId;

select appId , Sentiment , avg(Sentiment_Polarity) FROM rating , users GROUP BY appId , Sentiment;


select location, userId , appId , avg(Sentiment_Polarity) FROM rating , users WHERE users.id = rating.userId GROUP BY location, userId , appId;

select Category, userId , appId , avg(Sentiment_Polarity) FROM rating , apps WHERE apps.id = rating.appId GROUP BY Category, userId , appId;


select
avg(case when Rating > 4 and Rating <= 5 then Sentiment_Polarity else 0 end) as greater4,
avg(case when Rating > 3 and Rating <= 4 then Sentiment_Polarity else 0 end) as greater3
from rating , apps WHERE apps.id = rating.appId;

select
avg(case when Rating > 4.5 and Rating <= 5 then Sentiment_Polarity else 0 end) as greater45,
avg(case when Rating > 4 and Rating <= 4.5 then Sentiment_Polarity else 0 end) as greater4,
avg(case when Rating > 3.5 and Rating <= 4 then Sentiment_Polarity else 0 end) as greater35,
avg(case when Rating > 3 and Rating <= 3.5 then Sentiment_Polarity else 0 end) as greater3
from rating , apps WHERE apps.id = rating.appId;


select appId , avg(Sentiment_Polarity) FROM rating GROUP BY appId;
select version , appId , avg(Sentiment_Polarity) FROM rating, users WHERE rating.userId = users.id GROUP BY version , appId;

select Category, avg(Sentiment_Polarity) FROM rating , apps WHERE apps.id = rating.appId GROUP BY Category ;

select location , avg(Sentiment_Polarity) FROM rating , users WHERE users.id = rating.userId AND rating.appId = 0 GROUP BY location ;
select appId , location , avg(Sentiment_Polarity) FROM rating , users WHERE users.id = rating.userId GROUP BY location , appId;



select location , Category , avg(Sentiment_Polarity) FROM rating , users, apps WHERE users.id = rating.userId AND rating.appId = apps.id AND ( Category = "ENTERTAINMENT" || Category = "TOOLS" ) GROUP BY location , Category;


select avg(Sentiment_Polarity) , Category FROM rating , apps WHERE apps.id = rating.appId GROUP BY Category ;


select location , Category , avg(Sentiment_Polarity) FROM rating , users, apps WHERE users.id = rating.userId AND rating.appId = apps.id GROUP BY location , Category;

select location , Category , avg(Sentiment_Polarity) FROM rating , users, apps WHERE users.id = rating.userId AND rating.appId = apps.id AND ( Category = "ENTERTAINMENT" || Category = "TOOLS" ) AND ( location = "Napa" || location= "Edmond" ) GROUP BY location , Category;


select Category, version , avg(Sentiment_Polarity) FROM rating , apps , users WHERE apps.id = rating.appId AND users.id = rating.userId GROUP BY Category , version ;
select Category , avg(Sentiment_Polarity) FROM rating , apps , users WHERE apps.id = rating.appId AND users.id = rating.userId AND version = 9 GROUP BY Category , version ;



select Category , avg(rating) FROM rating , apps , users WHERE apps.id = rating.appId AND users.id = rating.userId GROUP BY Category;

select * from rating pivot( avg( Sentiment_Polarity ) for userId in (1,2,3) );
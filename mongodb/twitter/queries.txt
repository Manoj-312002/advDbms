db.users.insertMany([ 
    { name: "Ruthraj" , age : 26  , follow : { ing : 32 , wers : 1000 } } ,
    { name: "Ruthraj" , age : 52  , follow : { ing : 54 , wers : 10 } } ,
    { name: "du plessis" , age : 32 , follow : { ing : 43 , wers : 10000 } },
    { name: "Moeen Ali" , age : 28 , follow : { ing : 53 , wers : 10000 } },
    { name: "Rayudu" , age : 30, follow : { ing : 53 , wers : 1000 }  },
    { name: "Jadeja" , age : 25  , follow : { ing : 43 , wers : 100000 }}
])

db.comments.insertMany([
    {
        content : "great match",
        id : 1,
        replies : [
            { id: 2, content : "last over was great" , likes : 23 },
            { id: 3, content : "jadeja : finisher" , likes : 43 }
        ],
        likes : 54,
    },
    {
        content : "Thakur man of match" ,
        id : 2,
        replies : [
            { id:  3, content: "congrats Thakur" , likes : 84 },
        ],
        likes : 43,
    },
    {
        content : "Good team work" ,
        id : 1,
        replies : [
            { id:  4, content: "What a game" , likes : 23 },
        ],
        likes : 43,
    },
    {
        content : "poor playing" ,
        id : 10,
        replies : [
        ],
        likes : 0,
    },

])

db.users.find({})
db.users.find( { name: "Jadeja" } )
db.users.find({ 
    name : { $in : [ "Jadeja" , "Rayudu" ] }
 })

// Queries 

db.users.find({
    name : "Ruthraj",
    age : { $lt : 30 }
})

db.users.find({
    $or : [
        { age : { $gt : 40 } } ,
        { name : "Rayudu" }
    ]
})

db.users.find({
    age : { $gt : 29 },
    $or : [
        { age : { $gt : 40 } } ,
        { name : "Rayudu" }
    ]
})


db.users.find({
    "follow.wers" : { $gt : 2000 }
})

db.comments.find({
    "replies.likes" : { $gt : 40 }
})

# atleast one 
db.comments.find({
    "replies.likes" : { $gt : 50 }
})

db.comments.find(
    { "replies.0.likes" : { $gt : 50 } },
    { replies : 1 , _id : 0  }
)

// update

db.users.updateMany(
    { age : { $lt : 35 } },
    {
        $set : { profession : "cricket" }
    }
)

db.comments.updateMany(
    { id : 1 },
    { $inc : { comments : 1  } }
)

// delete 

db.users.deleteMany({
    name : "Ruthraj",
    age : { $gt : 40 }
})

db.users.drop()



// index 

db.users.createIndex({ name : 1 , "follow.ing" : -1 })
db.comments.createIndex({ likes : -1 })

db.comments.find().sort({ likes : -1 })


// aggregation

db.comments.aggregate([
    { $match : { likes : { $gt : 20 } } },
    { $group : { _id : "$id" , total_likes : { $sum : "$likes" }  }  }
])

db.comments.aggregate([
    { $unwind : "$replies" },
    { $group : { _id : "$_id" , reply_likes : { $sum : "$replies.likes" } } }

])

db.comments.aggregate([
    { $unwind : "$replies" },
    { $group : { _id : "$_id"  , max_reply_likes : { $max : "$replies.likes" } } }
])

db.comments.aggregate([
    { $project : { _id : 0, content : 1 , replies : 1 } },
    { $limit : 2 }
])

db.comments.aggregate([
    { $project : { _id : 0, content : 1 , replies : 1 } },
    { $skip : 2 }
])

db.users.aggregate([
    { $project : { follow : 1 , _id : 0 , name : 1 } },
    { $sort : { "follow.wers" : -1 , "follow.ing" : 1  } },
])
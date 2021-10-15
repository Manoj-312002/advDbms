<?php
require 'connect.php';

echo "<h1> Genre count : </h1>";

$q = $db->list->aggregate([
    [ "\$unwind" => "\$tags" ],
    [ 
        "\$group" => [ 
            "_id" => "\$tags" ,
            "ct" => [ "\$sum" => 1 ],
            "likes" => [ "\$sum" => "\$likes" ]
        ] 
    ],
    [ "\$sort" => 
        [ 
            "likes" => -1 ,
            "ct" => -1
        ] 
    ]
]);

foreach( $q as $r )
    echo "<p> " . $r['_id'] . "  --------  Likes : " . $r['likes'] . "  -----  Count :" . $r['ct'] . "</p>" ;


$q = $db->list->aggregate([
    [   
        "\$group" => [
            "_id" => "null" ,
            "maxL" => [ "\$max" => "\$likes" ] ,
            "avgL" => [ "\$avg" => "\$likes" ] ,
            "maxR" => [ "\$max" => "\$rating" ] ,
            "avgR" => [ "\$avg" => "\$rating" ]
        ]
    ],
    [
        "\$project" => [
            "maxL" => 1 ,
            "avgL" => 1 ,
            "maxR" => 1 ,
            "avgR" => 1 ,
        ]
    ]
]);

echo "</br>";

foreach( $q as $r ){
    $av = $r['avgL'];
    echo "<h3> Maximum likes : ". $r['maxL'] ."</h3>";
    echo "<h3> Average likes : ". $r['avgL'] ."</h3>";
    echo "<h3> Maximum Rating : ". $r['maxR'] ."</h3>";
    echo "<h3> Average Rating : ". $r['avgR'] ."</h3>";
}
    

echo "</br>";

echo "<h1> Average movies </h1>";

$q = $db->list->find(
    [
        "\$and" => [
            [ "likes" => [ "\$lte" => $av + 1 ] ],
            [ "likes" => [ "\$gte" => $av - 1 ] ] ,
        ]
    ],
    [
        [
            "title" => 1,
            "likes" => 1
        ]
    ]
);

foreach( $q as $r )
    echo "<p> " . $r['title'] . "  --------  Likes : " . $r['likes'] . "</p>" ;


?>
<?php
require 'connect.php';
$title = $_POST['title'];
$rating = floatval($_POST['rating'] );
$tags =  explode (",",  $_POST['tags'] );

$db->list->insertOne([
    "title" => $title ,
    "rating" => $rating ,
    "tags" => $tags ,
    "likes" => 0,
]);
header("Location:index.php");
?>
<?php
require 'connect.php';
if( isset( $_POST['up'] ) ){
    $st =  new MongoDB\BSON\ObjectId ( $_POST['up'] );
    $db->list->updateOne(
        [ "_id" => $st ] ,
        [ "\$inc" => [ "likes" => 1  ] ]
    );
}
if( isset( $_POST['down'] ) ){
    $st =  new MongoDB\BSON\ObjectId ( $_POST['down'] );
    $e = $db->list->deleteOne([
        "_id" => $st
    ]);
}
header("Location:index.php");
?>
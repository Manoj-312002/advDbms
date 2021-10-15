<?php
require 'vendor/autoload.php';

$client = new MongoDB\Client(
    'mongodb://localhost:27017'
);

$db = $client->twitter;
$cursor = $db->comments->aggregate([
   [ "\$project" => [
           "likes" => 1
   ] ]
]);
foreach( $cursor as $document ){
   echo $document["likes"] . "\n";
}
<?php
require 'connect.php';

$db->list->updateMany(
    [],
    [ "\$set" => [ "towatch" => "yes" ] ]
);

$db->list->updateMany(
    [ "likes" => [ "\$gt" => 5 ] ],
    [ "\$inc" => [ "rating" => 0.1 ] ]
);

?>
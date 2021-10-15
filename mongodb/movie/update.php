<?php
require 'connect.php';

$db->list->updateMany(
    [],
    [ "\$set" => [ "towatch" => "yes" ] ]
)

?>
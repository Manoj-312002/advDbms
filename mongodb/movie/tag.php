<?php
    require 'connect.php';
    echo "<h1>" . $_GET['tag'] . " which is not Thriller </h1>";
    $q = $db->list->find([
        "tags" => [ "\$elemMatch" => [ "\$eq" => $_GET['tag'] ] , "\$ne" => "thriller" ]
    ]);
    foreach( $q as $r ){
        echo "<p>" . $r['title'] ."</p>";
    }

    echo "<h1>" . "All Thriller" . "</h1>";
    $q = $db->list->find([
        "tags" => "thriller"
    ]);
    foreach( $q as $r ){
        echo "<p>" . $r['title'] ."</p>";
    }
?>  
<?php
require 'connect.php';

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/styles.css"/>
    <title>Movies</title>
</head>
<body>
    <div class="container">
        <h1> Enter movie details </h1>
        <form method="post" action="add.php">
            <input type="text" name="title" placeholder="movie title"> </br>
            <input type="text" name="rating" placeholder="rating"> </br>
            <input type="text" name="tags" placeholder="tags" > </br>
            <input type="submit" value="add">
        </form>
    </div>
    <div class="view" >
        <h1> Movies </h1>
        <form method="post" action="remove.php" > 
            <?php
                foreach( $db->list->find() as $r ){
                    $id = $r['_id'];
                    echo "<p>" . 
                        "" . $r['title'] . "" .
                        "<label> " . $r['rating'] . "</label>" . 
                        "<label> " . $r['likes'] . "</label>" .
                        "<button name='up' type='submit' value='$id' >^</button> " .
                        "<button name='down' type='submit' value='$id' >X</button>". 
                    "</p> " ;
                }
            ?>
        </form>
    </div>

    <div>
        <h1> Get Non thriller But </h1>
        <form method="get" action="tag.php" >
            <input type="text" name="tag" /> </br>
            <input type="submit" value="get" />
        </form>
    </div>
    
</body>
</html> 
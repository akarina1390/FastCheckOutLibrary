<?php
    error_reporting(0);
    require "init.php";
    
    $bookname = $_GET["bookname"];
    $authorfirstname = $_GET["authorfirstname"];
    $authorlastname = $_GET["authorlastname"];
    $publishername = $_GET["publishername"];
    $year =  $_GET["year"];
    $edition = $_GET["edition"];
    $genre = $_GET["genre"];
    $library = $_GET["library"];

    $sql = "INSERT INTO BOOK (NAME, YEAR, EDITION, GENRE_ID, AUTHOR_ID, PUBLISHER_ID) SELECT '$bookname', $year, $edition, $genre, AUTHOR.ID, PUBLISHER.ID FROM PUBLISHER, AUTHOR WHERE PUBLISHER.NAME LIKE '%$publishername%' AND AUTHOR.FIRST_NAME LIKE '%$authorfirstname%' AND AUTHOR.LAST_NAME LIKE  '%$authorlastname%';";

    if (mysqli_query($con,$sql)) {
        $last_id = $con->insert_id;

        $sql = "INSERT INTO BOOK_LIBRARY_XREF (BOOK_ID, LIBRARY_ID) VALUES ($last_id, $library);";

        if(!mysqli_query($con, $sql)){
            echo '{"message":"Unable to save the relationship data to the database. $last_id"}';
        }
    } else {
        echo '{"message":"Unable to save the data to the database."}';
    }

    ?>
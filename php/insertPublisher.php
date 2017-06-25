<?php
    error_reporting(0);
    require "init.php";
    
    $publishername = $_GET["publishername"];
    $country = $_GET["country"];
    
    $sql = "INSERT INTO PUBLISHER (NAME, COUNTRY_ID) VALUES ('$publishername', $country);";
    
    if(!mysqli_query($con, $sql)){
        echo '{"message":"Unable to save the data to the database."}';
    }
    ?>
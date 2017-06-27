<?php
    error_reporting(0);
    require "init.php";
    
    $firstname = $_GET["firstname"];
    $lastname = $_GET["lastname"];
    $gender = $_GET["gender"];
    $country = $_GET["country"];
    
    $sql = "INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME, COUNTRY_ID, GENDER_ID) VALUES ('$firstname', '$lastname', $country, $gender);";
    
    if(!mysqli_query($con, $sql)){
        echo '{"message":"Unable to save the data to the database."}';
    }
    ?>
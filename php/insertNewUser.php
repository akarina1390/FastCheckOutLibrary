<?php
    error_reporting(0);
    require "init.php";
    
    $username = $_GET["username"];
    $password = $_GET["password"];
    $email = $_GET["email"];
    $country = $_GET["country"];
    $gender = $_GET["gender"];
    $privilege = $_GET["privilege"];
    
    $sql = "INSERT INTO USER_CONF (USER_NAME, PASSWORD, EMAIL, GENDER_ID, PRIVILEDGE_ID, COUNTRY_ID) VALUES ('$username', '$password', '$email', $gender, $privilege, $country);";
    
    if(!mysqli_query($con, $sql)){
        echo '{"message":"Unable to save the data to the database."}';
    }
?>
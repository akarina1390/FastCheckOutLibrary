<?php 
error_reporting(0);
require "init.php";

$username = $_GET["username"];
$password = $_GET["password"];

$sql = "SELECT ID, USER_NAME, PASSWORD, PRIVILEDGE_ID FROM USER_CONF WHERE USER_NAME='".$username."' AND PASSWORD='".$password."';";
    #$sql = "SELECT ID, FIRST_NAME, LAST_NAME, PRIVILEDGE_ID FROM USER_CONF WHERE USER_NAME='anaka' AND PASSWORD='123456';";


$result = mysqli_query($con, $sql);

$response = array();

while($row = mysqli_fetch_array($result)){
	$response = array("id"=>$row[0],"firstName"=>$row[1],"lastName"=>$row[2],"priviledgeID"=>$row[3]);
}

echo json_encode(array("user_data"=>$response));

?>
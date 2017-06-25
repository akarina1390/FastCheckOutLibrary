<?php 
error_reporting(0);
require "init.php";

$username = $_GET["username"];
$password = $_GET["password"];

$sql = "SELECT ID, PRIVILEDGE_ID FROM USER_CONF WHERE USER_NAME='".$username."' AND PASSWORD='".$password."';";

$result = mysqli_query($con, $sql);

$response = array();

while($row = mysqli_fetch_array($result)){
	$response = array("id"=>$row[0],"priviledgeID"=>$row[1]);
}

echo json_encode(array("user_data"=>$response));

?>
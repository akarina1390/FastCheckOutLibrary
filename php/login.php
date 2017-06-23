<?php 
error_reporting(0);
require "init.php";

$username = $_POST["username"];
$password = $_POST["password"];

$sql = "SELECT ID, USER_NAME, PASSWORD, PRIVILEDGE_ID 
		FROM `USER_CONF` 
		WHERE `USER_NAME`='".$username."' 
		AND `PASSWORD`='".$password."';";

$result = mysqli_query($con, $sql);

$response = array();

while($row = mysqli_fetch_array($result)){
	$response = array("id"=>$row[0],"username"=>$row[1],"password"=>$row[2],"priviledgeID"=>$row[3]);
}

echo json_encode(array("user_data"=>$response));

?>
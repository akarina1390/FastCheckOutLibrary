<?php 
error_reporting(0);
require "init.php";

$firstname = $_GET["firstname"];
$lastname = $_GET["lastname"];
$country = $_GET["country"];
$gender = $_GET["gender"];

$sql = "SELECT BOOK.NAME, BOOK.EDITION FROM BOOK JOIN AUTHOR ON BOOK.AUTHOR_ID = AUTHOR.ID WHERE AUTHOR.FIRST_NAME LIKE '%$first_name%' AND AUTHOR.LAST_NAME LIKE '%$last_name%' AND AUTHOR.GENDER_ID = $gender AND AUTHOR.COUNTRY_ID = $country;";

$result = mysqli_query($con, $sql);
$response = array();
while($row = mysqli_fetch_array($result)){
	$response = array("id"=>$row[0],"bookname"=>$row[1],"bookedition"=>$row[2]);
}
echo json_encode(array("user_data"=>$response));
?>
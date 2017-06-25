<?php 
error_reporting(0);
require "init.php";

$bookname = $_GET["bookname"];
$publishername = $_GET["publishername"];
$bookyear = $_GET["bookyear"];
$genre = $_GET["genre"];

$sql = "SELECT BOOK.NAME, BOOK.EDITION FROM BOOK JOIN PUBLISHER ON PUBLISHER.ID = BOOK.PUBLISHER_ID WHERE PUBLISHER.NAME LIKE '%$publishername%' AND BOOK.YEAR = $bookyear AND BOOK.GENRE_ID = $genre AND BOOK.NAME LIKE '%$bookname%';";

$result = mysqli_query($con, $sql);
$response = array();
while($row = mysqli_fetch_array($result)){
	$response = array("id"=>$row[0],"bookname"=>$row[1],"bookedition"=>$row[2]);
}
echo json_encode(array("user_data"=>$response));
?>
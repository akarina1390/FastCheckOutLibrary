<?php 
error_reporting(0);
require "init.php";

$firstname = $_GET["firstname"];
$lastname = $_GET["lastname"];
$country = $_GET["country"];
$gender = $_GET["gender"];

$sql = "SELECT BOOK.ID, BOOK.NAME, BOOK.EDITION, LIBRARY.NAME LIBRARY_NAME, LIBRARY.ADDRESS LIBRARY_ADDRESS FROM BOOK JOIN AUTHOR ON BOOK.AUTHOR_ID = AUTHOR.ID JOIN BOOK_LIBRARY_XREF ON BOOK_LIBRARY_XREF.BOOK_ID = BOOK.ID JOIN LIBRARY ON LIBRARY.ID = BOOK_LIBRARY_XREF.LIBRARY_ID WHERE AUTHOR.FIRST_NAME LIKE '%$first_name%' AND AUTHOR.LAST_NAME LIKE '%$last_name%' AND AUTHOR.GENDER_ID = $gender AND AUTHOR.COUNTRY_ID = $country;";

$result = mysqli_query($con, $sql);
$response = array();
while($row = mysqli_fetch_array($result)){
    $response[] = array("bookid"=>$row[0],"bookname"=>$row[1],"bookedition"=>$row[2],"libraryname"=>$row[3],"libraryaddress"=>$row[4]);
}
echo json_encode(array("user_data"=>$response));
?>
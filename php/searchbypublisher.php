<?php 
error_reporting(0);
require "init.php";
require_once "book.php";

$publishername = $_GET["publishername"];
$country = $_GET["country"];

$sql = "SELECT BOOK.ID, BOOK.NAME, BOOK.EDITION, LIBRARY.NAME LIBRARY_NAME, LIBRARY.ADDRESS LIBRARY_ADDRESS FROM BOOK JOIN PUBLISHER ON PUBLISHER.ID = BOOK.PUBLISHER_ID JOIN BOOK_LIBRARY_XREF ON BOOK.ID = BOOK_LIBRARY_XREF.BOOK_ID JOIN LIBRARY ON BOOK_LIBRARY_XREF.LIBRARY_ID = LIBRARY.ID  WHERE PUBLISHER.NAME LIKE '%$publishername%' AND PUBLISHER.COUNTRY_ID = $country;";

$result = mysqli_query($con, $sql);
$response = array();

while($row = mysqli_fetch_array($result)){
    $book = new Book($row[0], $row[1], $row[2], $row[3], $row[4]);
    $response[] = $book;
}

echo json_encode($response);
?>
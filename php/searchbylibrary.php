<?php 
error_reporting(0);
require "init.php";
require_once "book.php";

$libraryname = $_GET["libraryname"];
$address = $_GET["address"];
$country = $_GET["country"];

$sql = "SELECT BOOK.ID, BOOK.NAME, BOOK.EDITION, LIBRARY.NAME LIBRARY_NAME, LIBRARY.ADDRESS LIBRARY_ADDRESS FROM BOOK JOIN BOOK_LIBRARY_XREF ON BOOK_LIBRARY_XREF.BOOK_ID = BOOK.ID JOIN LIBRARY ON BOOK_LIBRARY_XREF.LIBRARY_ID = LIBRARY.ID WHERE LIBRARY.NAME LIKE '%$libraryname%' AND LIBRARY.ADDRESS LIKE '%$address%' AND LIBRARY.COUNTRY_ID = $country";

$result = mysqli_query($con, $sql);
$response = array();

while($row = mysqli_fetch_array($result)){
    $book = new Book($row[0], $row[1], $row[2], $row[3], $row[4]);
    $response[] = $book;
}

echo json_encode($response);
?>
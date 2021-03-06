<?php 
error_reporting(0);
require "init.php";
require_once "book.php";

$bookname = $_GET["bookname"];
$publishername = $_GET["publishername"];
$bookyear = $_GET["bookyear"];
$genre = $_GET["genre"];
$edition = $_GET["edition"];

$sql = "SELECT BOOK.ID, BOOK.NAME, BOOK.EDITION, LIBRARY.NAME LIBRARY_NAME, LIBRARY.ADDRESS LIBRARY_ADDRESS FROM BOOK JOIN PUBLISHER ON PUBLISHER.ID = BOOK.PUBLISHER_ID JOIN BOOK_LIBRARY_XREF ON BOOK_LIBRARY_XREF.BOOK_ID = BOOK.ID JOIN LIBRARY ON BOOK_LIBRARY_XREF.LIBRARY_ID = LIBRARY.ID WHERE PUBLISHER.NAME LIKE '%$publishername%' AND BOOK.YEAR = $bookyear AND BOOK.GENRE_ID = $genre AND BOOK.NAME LIKE '%$bookname%' AND BOOK.EDITION=$edition;";

$result = mysqli_query($con, $sql);
$response = array();

while($row = mysqli_fetch_array($result)){
    $book = new Book($row[0], $row[1], $row[2], $row[3], $row[4]);
    $response[] = $book;
}

echo json_encode($response);
?>
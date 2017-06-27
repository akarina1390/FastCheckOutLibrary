<?php
    class Book {
        function Book($id, $bookname, $bookedition, $libraryname, $libraryaddress) {
            $this->id =$id;
            $this->bookname = $bookname;
            $this->bookedition = $bookedition;
            $this->libraryname = $libraryname;
            $this->libraryaddress = $libraryaddress;
        }
    }
?>

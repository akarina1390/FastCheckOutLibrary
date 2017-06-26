package com.fastcheckoutlibrary.objects;

import java.util.ArrayList;

public class Book {
    private String id, name, edition, libraryName, addressName;
    private static ArrayList<Book> bookList;

    public static  void setBookList(ArrayList<Book> theList) {
        ArrayList<Book> bookList = new ArrayList<Book>(theList);
    }

    public static ArrayList<Book> getList(){
        return bookList;
    }

    public Book(String bookid, String bookname, String bookEdition, String libName, String addrName){
        id = bookid;
        name = bookname;
        edition = bookEdition;
        libraryName = libName;
        addressName = addrName;
    }

    public String getId(){ return id;}
    public String getName(){ return name;}
    public String getEdition() { return edition;}
    public String getLibraryName(){ return libraryName;}
    public String getAddressName(){ return addressName;}

}

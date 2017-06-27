package com.fastcheckoutlibrary.objects;

import java.util.ArrayList;

public class Book {
    private String id, name, edition, libraryName, addressName;
    private static ArrayList<Book> bookList = new ArrayList<Book>();

    public static  void setBookList(ArrayList<Book> theList) {
        for (Book aBook : theList) {
            bookList.add(aBook);
        }
    }

    public static ArrayList<Book> getList(){
        return new ArrayList<Book>(bookList);
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

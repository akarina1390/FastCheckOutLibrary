package com.fastcheckoutlibrary.backend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.objects.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchResults extends AppCompatActivity {
    ArrayList<Book> theBookList;
    ExpandableListView resultList;
    ExpandableListAdapter listAdapter;

    ArrayList<String> listDataHeader;
    HashMap<String, ArrayList<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //Testing Set
        Book testBook = new Book("TestID", "TestName", "TestEdition", "TestLib", "TestAddr");
        Book testBook1 = new Book("TestID1", "TestName1", "TestEdition1", "TestLib1", "TestAddr1");
        Book testBook2 = new Book("TestID2", "TestName2", "TestEdition2", "TestLib2", "TestAddr2");
        ArrayList<Book> testSet = new ArrayList<Book>();
        testSet.add(testBook);
        testSet.add(testBook1);
        testSet.add(testBook2);
        Book.setBookList(testSet);

        theBookList = Book.getList();

        if (theBookList.size() != 0){
            resultList = (ExpandableListView)findViewById(R.id.ListOfResults);
            prepareListData();
            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
            resultList.setAdapter(listAdapter);
        }
    }

    /*
 * Preparing the list data
 */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<String>>();

        // Adding header and child data
        for(int i = 0; i < theBookList.size(); i++){
            listDataHeader.add(theBookList.get(i).getName());
            ArrayList<String> bookDetails = new ArrayList<String>();
            bookDetails.add("Book Id: " + theBookList.get(i).getId());
            bookDetails.add("Book Name: " + theBookList.get(i).getName());
            bookDetails.add("Book Edition: " + theBookList.get(i).getEdition());
            bookDetails.add("Library: " + theBookList.get(i).getLibraryName());
            bookDetails.add("Address: " + theBookList.get(i).getAddressName());
            listDataChild.put(listDataHeader.get(i), bookDetails);
        }
    }

}



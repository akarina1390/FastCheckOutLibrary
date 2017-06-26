package com.fastcheckoutlibrary.backend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.objects.Book;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {
    ArrayList<Book> theBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        if (Book.getList() == null) theBookList = null;
        else{
            theBookList = new ArrayList<Book>(Book.getList());
        }
    }
}

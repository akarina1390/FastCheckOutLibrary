package com.fastcheckoutlibrary.backend;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.typecode.GenreTypeCode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchByBook extends AppCompatActivity {
    private Button buttonSearchBook;
    private EditText bookName, bookPublisher, bookYear, bookEdition;
    private Spinner bookGenre;

    private String bookNameValue, bookPublisherValue, bookYearValue, bookEditionValue, bookGenreValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_book);

        bookName = (EditText) findViewById(R.id.editTextSearchInputBookName);
        bookPublisher = (EditText) findViewById(R.id.editTextSearchInputPublisher);
        bookYear = (EditText) findViewById(R.id.editTextSearchYear);
        bookEdition = (EditText) findViewById(R.id.editTextSearchInputYear);
        bookGenre = (Spinner) findViewById(R.id.spinnerSearchByBookGenreList);

        buttonSearchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSearch(v);
            }
        });
    }

    public void setSearch(View v) {
        bookNameValue = bookName.getText().toString();
        bookPublisherValue = bookPublisher.getText().toString();
        bookYearValue = bookYear.getText().toString();
        bookEditionValue = bookEdition.getText().toString();
        bookGenreValue = GenreTypeCode.getID(bookGenre.getSelectedItem().toString());

        //SearchByBook.Background b = new SearchByBook().Background();
        //b.execute(bookNameValue, bookPublisherValue, bookYearValue,bookEditionValue,bookGenreValue);
    }

}

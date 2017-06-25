package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.typecode.CountryTypeCode;
import com.fastcheckoutlibrary.typecode.GenderTypeCode;
import com.fastcheckoutlibrary.typecode.GenreTypeCode;
import com.fastcheckoutlibrary.typecode.LibraryTypeCode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminInsertBook extends AppCompatActivity {
    private EditText bookName, publisherName, year, edition, authorName;
    private Spinner genre, library;
    private Button newBook;
    private String bookNameValue, publisherNameValue, yearValue, editionValue, genreValue, authorValue, libraryValue;
    Context ctx = this;
    private boolean INSERTED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert_book);

        bookName = (EditText) findViewById(R.id.editTextInsertInputFirstNameAuthor);
        publisherName = (EditText) findViewById(R.id.editTextInsertInputPublisherName);
        year = (EditText) findViewById(R.id.editTextInsertYear);
        edition = (EditText) findViewById(R.id.editTextInsertBookEdition);
        genre = (Spinner) findViewById(R.id.spinnerNewBookGenre);
        authorName = (EditText) findViewById(R.id.editTextInsertBookAuthor);
        library = (Spinner) findViewById(R.id.spinnerNewBookLibrary);
        newBook = (Button) findViewById(R.id.buttonInsertBook);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBook(v);
            }
        });
    }

    private void insertBook(View v) {
        bookNameValue = bookName.getText().toString();
        publisherNameValue = publisherName.getText().toString();
        yearValue = year.getText().toString();
        editionValue = edition.getText().toString();
        genreValue = GenreTypeCode.getID(genre.getSelectedItem().toString());
        libraryValue = LibraryTypeCode.getId(library.getSelectedItem().toString());
        authorValue = authorName.getText().toString();
        AdminInsertBook.Background b = new AdminInsertBook.Background();
        b.execute(bookNameValue, publisherNameValue, yearValue, editionValue, genreValue, authorValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String bookname = params[0];
            String publishername = params[1];
            String year = params[2];
            String edition = params[3];
            String genre = params[4];
            String author = params[5];
            String data = "";
            String authorFirstName = "";
            String authorLastName = "";
            int tmp;

            String[] authorFullName = author.split(" ");
            if (authorFullName.length == 2) {
                authorFirstName = authorFullName[0];
                authorLastName = authorFullName[1];
            }

            try {
                String urlpath = "http://192.168.86.100:8080/CPSC471/FastCheckOutLibrary/insertBook.php?";
                urlpath += "bookname=" + bookname;
                urlpath += "&authorfirstname=" + authorFirstName;
                urlpath += "&authorlastname=" + authorLastName;
                urlpath += "&publishername=" + publishername;
                urlpath += "&year=" + year;
                urlpath += "&edition=" + edition;
                urlpath += "&genre=" + genre;
                URL url = new URL(urlpath);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.flush();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                while ((tmp = inputStream.read()) != -1) {
                    data += (char) tmp;
                }
                inputStream.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception login into server";
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception within stream files";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == "") {
                INSERTED = true;
            } else {
                INSERTED = false;
            }
            if (INSERTED) {
                Intent intent = new Intent(ctx, AdminView.class);
                startActivity(intent);
            } else {
                Toast.makeText(ctx, "Error Inserting Book", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

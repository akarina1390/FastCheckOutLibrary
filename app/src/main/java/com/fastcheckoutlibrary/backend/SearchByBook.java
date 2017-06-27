package com.fastcheckoutlibrary.backend;

import android.content.Context;
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
import com.fastcheckoutlibrary.objects.Book;
import com.fastcheckoutlibrary.typecode.CountryTypeCode;
import com.fastcheckoutlibrary.typecode.GenderTypeCode;
import com.fastcheckoutlibrary.typecode.GenreTypeCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SearchByBook extends AppCompatActivity {
    private Button buttonSearchBook;
    private EditText bookName, bookPublisher, bookYear, bookEdition;
    private Spinner bookGenre;
    private Context ctx;

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
        buttonSearchBook = (Button) findViewById(R.id.buttonSearchBook);
        buttonSearchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });
    }

    private void search(View v) {
        bookNameValue = bookName.getText().toString();
        bookPublisherValue = bookPublisher.getText().toString();
        bookYearValue = bookYear.getText().toString();
        bookEditionValue = bookEdition.getText().toString();
        bookGenreValue = GenreTypeCode.getID(bookGenre.getSelectedItem().toString());

        SearchByBook.Background b = new SearchByBook.Background();
        b.execute(bookNameValue, bookPublisherValue, bookYearValue, bookEditionValue, bookGenreValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String bookname = params[0];
            String publishername = params[1];
            String bookyear = params[2];
            String genre = params[3];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://192.168.86.100:8080/CPSC471/FastCheckOutLibrary/searchbybook.php?";
                urlpath += "bookname=" + bookname;
                urlpath += "&publishername=" + publishername;
                urlpath += "&bookyear=" + bookyear;
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
            ArrayList<Book> list = new ArrayList<>();
            try {
                JSONArray jsonarray = new JSONArray(result);
                if (jsonarray.length() == 0) {
                    Toast.makeText(ctx, "No Data Found", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String id = jsonobject.getString("id");
                        String bookname = jsonobject.getString("bookname");
                        String bookedition = jsonobject.getString("bookedition");
                        String libraryname = jsonobject.getString("libraryname");
                        String libraryaddress = jsonobject.getString("libraryaddress");
                        Book book = new Book(id, bookname, bookedition, libraryname, libraryaddress);
                        list.add(book);
                    }
                    SearchResults.setBookList(list);
                    Intent intent = new Intent(ctx, SearchResults.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                Toast.makeText(ctx, "Error Searching by Publisher", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

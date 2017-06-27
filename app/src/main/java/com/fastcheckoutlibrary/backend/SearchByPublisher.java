package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.objects.Book;
import com.fastcheckoutlibrary.objects.IP;
import com.fastcheckoutlibrary.typecode.CountryTypeCode;

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

public class SearchByPublisher extends AppCompatActivity {
    private EditText newName;
    private Spinner newCountry;
    private Button newSearch;

    private String newNameValue, newCountryValue, newSearchValue;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_publisher);

        newName = (EditText) findViewById(R.id.editTextSearchInputPublisherName);
        newCountry = (Spinner) findViewById(R.id.editCountry);

        newSearch = (Button) findViewById(R.id.buttonSearchPublisher);
        newSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });
    }

    private void search(View v) {
        newNameValue = newName.getText().toString();
        newCountryValue =  CountryTypeCode.getId(newCountry.getSelectedItem().toString());

        SearchByPublisher.Background b = new SearchByPublisher.Background();
        b.execute(newNameValue, newCountryValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String country = params[1];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://";
                urlpath += IP.getIP();
                urlpath += ":8080/CPSC471/FastCheckOutLibrary/searchByPublisher.php?";
                urlpath += "publishername=" + name;
                urlpath += "&country=" + country;

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

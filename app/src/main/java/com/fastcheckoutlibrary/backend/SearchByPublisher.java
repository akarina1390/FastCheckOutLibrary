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

public class SearchByPublisher extends AppCompatActivity {
    private EditText newName;
    private Spinner newCountry;
    private Button newSearch;

    private String newNameValue, newCountryValue, newSearchValue;

    private String ID, NAME, AUTHORD_ID, PUBLISHER_ID, YEAR, EDITION, GENRE_ID;
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
                String urlpath = "http://192.168.86.100:8080/CPSC471/FastCheckOutLibrary/searchByPublisher.php?";
                urlpath += "name=" + name;
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
            String err = null;
            try {
                JSONArray json = new JSONArray(result);
                for (int i = 0; i < json.length(); i++)
                {
                    JSONArray array = json.getJSONArray(i);
                    for (int j = 0; j<array.length();i++)
                    {
                        JSONObject obj = array.getJSONObject(i);
                        //create Book object here
                        /*
                            //these would be parameters to the book object?
                            ID = user_data.getString("id");
                            NAME = user_data.getString("name");
                            AUTHORD_ID = user_data.getString("author_id");
                            PUBLISHER_ID = user_data.getString("publisher_id");
                            YEAR = user_data.getString("year");
                            EDITION = user_data.getString("edition");
                            GENRE_ID = user_data.getString("genre_id");
                            Book book = new book (ID, AUTHOR_ID, ...);
                         */
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception obtaining database result";
            }

            //this if-else was copied from NewUserAccount.java, not sure what to put here
            if (err == null) {
                if (true /*INSERTED*/) {
                    Intent intent = new Intent(ctx, ListOfBooksResults.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Error Searching by Publisher", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(ctx, "Error Searching by Publisher", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

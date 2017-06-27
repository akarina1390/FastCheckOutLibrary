package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.objects.IP;
import com.fastcheckoutlibrary.typecode.CountryTypeCode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminInsertPublisher extends AppCompatActivity {
    private EditText publisherName;
    private Spinner country;
    private Button newPublisher;
    private String publisherNameValue, countryValue;
    Context ctx = this;
    private boolean INSERTED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert_publisher);

        publisherName = (EditText) findViewById(R.id.editTextInsertInputPublisherName);
        country = (Spinner) findViewById(R.id.spinnerNewPublisherCountry);

        newPublisher = (Button) findViewById(R.id.buttonInsertPublisher);
        newPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAuthor(v);
            }
        });
    }

    private void insertAuthor(View v) {
        publisherNameValue = publisherName.getText().toString();
        countryValue = CountryTypeCode.getId(country.getSelectedItem().toString());
        AdminInsertPublisher.Background b = new AdminInsertPublisher.Background();
        b.execute(publisherNameValue, countryValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String publishername = params[0];
            String country = params[1];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://";
                urlpath += IP.getIP();
                urlpath += ":8080/CPSC471/FastCheckOutLibrary/insertPublisher.php?";
                urlpath += "publishername=" + publishername;
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
            if (result == "") {
                INSERTED = true;
            } else {
                INSERTED = false;
            }
            if (INSERTED) {
                Intent intent = new Intent(ctx, AdminView.class);
                startActivity(intent);
            } else {
                Toast.makeText(ctx, "Error Inserting Publisher", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

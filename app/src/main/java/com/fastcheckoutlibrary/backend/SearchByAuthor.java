package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.typecode.CountryTypeCode;
import com.fastcheckoutlibrary.typecode.DeathStatusTypeCode;
import com.fastcheckoutlibrary.typecode.GenderTypeCode;

public class SearchByAuthor extends AppCompatActivity {

    private EditText authorlastName, authorfirstName;
    private String lastnameValue, firstnameValue, countryValue, genderValue, deathStatusValue;
    private RadioButton maleSwitch, aliveSwitch;
    private Button searchAuthor;
    private Spinner countryList;
    private RadioGroup genderGroup, deathGroup;

    private String bookName, bookEdition, bookLocation;
    private int bookQuantity;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_author);

        authorfirstName = (EditText) findViewById(R.id.editTextSearchInputFirstNameAuthor);
        authorlastName = (EditText) findViewById(R.id.editTextSearchInputLastNameAuthor);
        maleSwitch = (RadioButton) findViewById(R.id.radioButtonSearchM);
        aliveSwitch = (RadioButton) findViewById(R.id.radioButtonSearchL);
        countryList = (Spinner) findViewById(R.id.editTextAuthorCountry);

        searchAuthor = (Button) findViewById(R.id.buttonSearchAuthor);
        searchAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSearch(v);
            }
        });
    }

    ;


    public void setSearch(View v) {
        firstnameValue = authorfirstName.getText().toString();
        lastnameValue = authorlastName.getText().toString();
        genderValue = genderGroup.getCheckedRadioButtonId() == maleSwitch.getId() ?
                GenderTypeCode.MALE.getID() : GenderTypeCode.FEMALE.getID();
        countryValue = CountryTypeCode.getId(countryList.getSelectedItem().toString());
        deathStatusValue = deathGroup.getCheckedRadioButtonId() == aliveSwitch.getId() ?
                DeathStatusTypeCode.ALIVE.getID() : DeathStatusTypeCode.DEAD.getID();
        SearchByAuthor.Background b = new SearchByAuthor.Background();
        b.execute(firstnameValue, lastnameValue, genderValue, countryValue, deathStatusValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String firstName = params[0];
            String lastName = params[1];
            String gender = params[2];
            String country = params[3];
            String deathStatus = params[4];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://192.168.86.100:8080/CPSC471/FastCheckOutLibrary/login.php?";
                urlpath += "firstname=" + firstName;
                urlpath += "&lastname=" + lastName;
                urlpath += "&gender=" + gender;
                urlpath += "&country=" + country;
                urlpath += "&deathstatus=" + deathStatus;
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
                JSONObject root = new JSONObject(result);
                JSONObject book_data = root.getJSONObject("book_data");
                bookName = book_data.getString("bookname");
                bookEdition = book_data.getString("bookedition");
                bookLocation = book_data.getString("booklocation");
                bookQuantity = book_data.getInt("bookquantity");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception obtaining database result";
            }

            if (err == null) {
                Intent myIntent = new Intent(SearchByAuthor.this, SearchResults.class);
                myIntent.putExtra("BOOKNAME", bookName);
                myIntent.putExtra("BOOKEDITION", bookEdition);
                myIntent.putExtra("BOOKLOCATION", bookLocation);
                myIntent.putExtra("BOOKQUANTITY", bookQuantity);
                startActivityForResult(myIntent, 0);
            } else {
                Toast.makeText(ctx, "Error Login in", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

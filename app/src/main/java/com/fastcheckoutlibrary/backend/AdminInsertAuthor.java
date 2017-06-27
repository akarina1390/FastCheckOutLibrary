package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.objects.IP;
import com.fastcheckoutlibrary.typecode.CountryTypeCode;
import com.fastcheckoutlibrary.typecode.GenderTypeCode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminInsertAuthor extends AppCompatActivity {
    private EditText firstName, lastName;
    private RadioGroup gender;
    private RadioButton male, female;
    private Spinner country;
    private Button newAuthor;
    private String firstNameValue, lastNameValue, genderValue, countryValue;
    Context ctx = this;
    private boolean INSERTED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert_author);

        firstName = (EditText) findViewById(R.id.editTextInsertInputFirstNameAuthor);
        lastName = (EditText) findViewById(R.id.editTextInsertInputLastNameAuthor);
        gender = (RadioGroup) findViewById(R.id.radioGroupNewGender);
        male = (RadioButton) findViewById(R.id.radioButtonInsertM);
        female = (RadioButton) findViewById(R.id.radioButtonInsertF);
        country = (Spinner) findViewById(R.id.spinnerNewAuthorCountry);

        newAuthor = (Button) findViewById(R.id.buttonInsertAuthor);
        newAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAuthor(v);
            }
        });
    }

    private void insertAuthor(View v) {
        firstNameValue = firstName.getText().toString();
        lastNameValue = lastName.getText().toString();
        genderValue = gender.getCheckedRadioButtonId() == male.getId() ?
                GenderTypeCode.MALE.getID() : GenderTypeCode.FEMALE.getID();
        countryValue = CountryTypeCode.getId(country.getSelectedItem().toString());
        AdminInsertAuthor.Background b = new AdminInsertAuthor.Background();
        b.execute(firstNameValue, lastNameValue, genderValue, countryValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String firstname = params[0];
            String lastname = params[1];
            String gender = params[2];
            String country = params[3];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://";
                urlpath += IP.getIP();
                urlpath +=":8080/CPSC471/FastCheckOutLibrary/insertAuthor.php?";
                urlpath += "firstname=" + firstname;
                urlpath += "&lastname=" + lastname;
                urlpath += "&gender=" + gender;
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
                Toast.makeText(ctx, "Error Inserting Author", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

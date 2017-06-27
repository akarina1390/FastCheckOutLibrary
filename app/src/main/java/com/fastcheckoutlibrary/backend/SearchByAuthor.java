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

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;
import com.fastcheckoutlibrary.objects.IP;
import com.fastcheckoutlibrary.typecode.CountryTypeCode;
import com.fastcheckoutlibrary.typecode.GenderTypeCode;
import com.fastcheckoutlibrary.objects.Book;

public class SearchByAuthor extends AppCompatActivity {

    private EditText authorlastName, authorfirstName;
    private String lastnameValue, firstnameValue, countryValue, genderValue;
    private RadioButton maleSwitch;
    private Button searchAuthor;
    private Spinner countryList;
    private RadioGroup genderGroup;

    private ArrayList<Book> searchedBooks;


    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_author);

        authorfirstName = (EditText) findViewById(R.id.editTextSearchInputFirstNameAuthor);
        authorlastName = (EditText) findViewById(R.id.editTextSearchInputLastNameAuthor);
        genderGroup = (RadioGroup) findViewById(R.id.radioGroupAuthorGender);
        maleSwitch = (RadioButton) findViewById(R.id.radioButtonSearchM);
        countryList = (Spinner) findViewById(R.id.editTextAuthorCountry);

        searchAuthor = (Button) findViewById(R.id.buttonSearchBookByAuthor);
        searchAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });
    }

    public void search(View v) {
        firstnameValue = authorfirstName.getText().toString();
        lastnameValue = authorlastName.getText().toString();
        genderValue = genderGroup.getCheckedRadioButtonId() == maleSwitch.getId() ?
                GenderTypeCode.MALE.getID() : GenderTypeCode.FEMALE.getID();
        countryValue = CountryTypeCode.getId(countryList.getSelectedItem().toString());
        SearchByAuthor.Background b = new SearchByAuthor.Background();
        b.execute(firstnameValue, lastnameValue, genderValue, countryValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String firstName = params[0];
            String lastName = params[1];
            String gender = params[2];
            String country = params[3];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://";
                urlpath += IP.getIP();
                urlpath += ":8080/CPSC471/FastCheckOutLibrary/searchbyauthor.php?";
                urlpath += "firstname=" + firstName;
                urlpath += "&lastname=" + lastName;
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

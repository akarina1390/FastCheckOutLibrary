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

public class NewUserAccount extends AppCompatActivity {
    private EditText newUserName, newPassword, newEmail;
    private RadioGroup newRadioGroup;
    private RadioButton newMale, newFemale;
    private Spinner newCountry;
    private Button newUser;
    Context ctx = this;
    private boolean INSERTED;
    private static int _userPrivilege;

    private String newUserValue, newPasswordValue, newEmailValue, newCountryValue, newGenderValue;

    public static void setUserPrivilege(int userPrivilege) {
        _userPrivilege = userPrivilege;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_account);

        newUserName = (EditText) findViewById(R.id.editTextNewUserInput);
        newPassword = (EditText) findViewById(R.id.editTextNewPassword);
        newEmail = (EditText) findViewById(R.id.editTextEmail);
        newRadioGroup = (RadioGroup) findViewById(R.id.radioGroupNewGender);
        newMale = (RadioButton) findViewById(R.id.radioButtonM);
        newFemale = (RadioButton) findViewById(R.id.radioButtonF);
        newCountry = (Spinner) findViewById(R.id.spinnerNewUserCountry);
        newUser = (Button) findViewById(R.id.buttonNewUser);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser(v);
            }
        });
    }

    private void createUser(View v) {
        newUserValue = newUserName.getText().toString();
        newPasswordValue = newPassword.getText().toString();
        newEmailValue = newEmail.getText().toString();
        newCountryValue = CountryTypeCode.getId(newCountry.getSelectedItem().toString());
        newGenderValue = newRadioGroup.getCheckedRadioButtonId() == newMale.getId() ?
                GenderTypeCode.MALE.getID() : GenderTypeCode.FEMALE.getID();
        NewUserAccount.Background b = new NewUserAccount.Background();
        b.execute(newUserValue, newPasswordValue, newEmailValue, newCountryValue, newGenderValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String userName = params[0];
            String password = params[1];
            String email = params[2];
            String country = params[3];
            String gender = params[4];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://192.168.86.100:8080/CPSC471/FastCheckOutLibrary/createNewUser.php?";
                urlpath += "username=" + userName;
                urlpath += "&password=" + password;
                urlpath += "&email=" + email;
                urlpath += "&country=" + country;
                urlpath += "&gender=" + gender;
                urlpath += "&privilege=" + String.valueOf(_userPrivilege);
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
                JSONObject user_data = root.getJSONObject("user_data");
                INSERTED = user_data.getBoolean("inserted");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception obtaining database result";
            }

            if (err == null) {
                if (INSERTED) {
                    Intent intent = new Intent(ctx, AdminView.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Error Inserting New User", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(ctx, "Error Inserting New User", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

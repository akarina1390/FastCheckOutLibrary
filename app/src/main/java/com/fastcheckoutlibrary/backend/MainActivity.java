package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button login, createUser;
    private String usernameValue, passwordValue;
    private String ID, PRIVILEDGE_ID;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.editTextUserNameInput);
        password = (EditText) findViewById(R.id.editTextPasswordInput);
        login = (Button) findViewById(R.id.buttonSignIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin(v);
            }
        });
        createUser = (Button) findViewById(R.id.buttonNewUser);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, NewUserAccount.class);
                NewUserAccount.setUserPrivilege(2);
                startActivity(intent);
            }
        });
    }

    public void setLogin(View v) {
        usernameValue = username.getText().toString();
        passwordValue = password.getText().toString();
        Background b = new Background();
        b.execute(usernameValue, passwordValue);
    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String userName = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
                String urlpath = "http://192.168.86.100:8080/CPSC471/FastCheckOutLibrary/login.php?";
                urlpath += "username=" + userName;
                urlpath += "&password=" + password;
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
                ID = user_data.getString("id");
                PRIVILEDGE_ID = user_data.getString("priviledgeID");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception obtaining database result";
            }

            if (err == null) {
                if (PRIVILEDGE_ID.equals("1")) {
                    Intent intent = new Intent(ctx, AdminView.class);
                    //intent.putExtra("ID", ID);
                    startActivity(intent);
                } else if (PRIVILEDGE_ID.equals("2")) {
                    Intent intent = new Intent(ctx, AdminView.class);
                    //Intent intent = new Intent(ctx, UserView.class);
                    //intent.putExtra("ID", ID);
                    startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Error Login in", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(ctx, "Error Login in", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

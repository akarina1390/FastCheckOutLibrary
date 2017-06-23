package com.example.anakarinacarrocci.fastcheckoutlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signIn = (Button) findViewById(R.id.buttonSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UserSearchMainMenu.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button userRegister = (Button) findViewById(R.id.buttonNewUser);
        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NewUserAccount.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

}

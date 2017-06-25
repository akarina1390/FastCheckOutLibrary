package com.fastcheckoutlibrary.backend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;

public class UserSearchMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_main_menu);

        Button searchBook = (Button) findViewById(R.id.buttonSearchBook);
        searchBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UserSearchMainMenu.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button searchAuthor = (Button) findViewById(R.id.buttonSearchAuthor);
        searchAuthor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NewUserAccount.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button searchPublisher = (Button) findViewById(R.id.buttonSearchPublisher);
        searchPublisher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UserSearchMainMenu.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button searchLibrary = (Button) findViewById(R.id.buttonSearchLibrary);
        searchLibrary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NewUserAccount.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}

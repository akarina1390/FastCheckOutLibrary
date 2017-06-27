package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;

public class UserSearchMainMenu extends AppCompatActivity {
    Context ctx = this;
    Button searchBook, searchAuthor, searchPublisher, searchLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_main_menu);

        searchBook = (Button) findViewById(R.id.buttonSearchByBook);
        searchBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ctx, SearchByBook.class);
                startActivity(intent);
            }
        });

        searchAuthor = (Button) findViewById(R.id.buttonSearchByAuthor);
        searchAuthor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ctx, SearchByAuthor.class);
                startActivity(intent);
            }
        });

        searchPublisher = (Button) findViewById(R.id.buttonSearchByPublisher);
        searchPublisher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ctx, SearchByPublisher.class);
                startActivity(intent);
            }
        });

        searchLibrary = (Button) findViewById(R.id.buttonSearchByLibrary);
        searchLibrary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ctx, SearchByLibrary.class);
                startActivity(intent);
            }
        });
    }
}

package com.fastcheckoutlibrary.backend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anakarinacarrocci.fastcheckoutlibrary.R;

public class AdminView extends AppCompatActivity {
    private Button newUser, searchBook, newBook, newAuthor, newPublisher;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_view);

        newUser = (Button) findViewById(R.id.buttonNewAdm);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, NewUserAccount.class);
                NewUserAccount.setUserPrivilege(1);
                startActivity(intent);
            }
        });
        searchBook = (Button) findViewById(R.id.buttonSearchAdm);
        searchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, UserSearchMainMenu.class);
                startActivity(intent);
            }
        });
        newBook = (Button) findViewById(R.id.buttonInsertBook);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, AdminInsertBook.class);
                startActivity(intent);
            }
        });
        newAuthor = (Button) findViewById(R.id.buttonInsertAuthorAdm);
        newAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, AdminInsertAuthor.class);
                startActivity(intent);
            }
        });
        newPublisher = (Button) findViewById(R.id.buttonInsertNewPublisher);
        newPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, AdminInsertPublisher.class);
                startActivity(intent);
            }
        });
    }
}

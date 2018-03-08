package com.example.elessar1992.realmexample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView myUsername;
    private EditText eventtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myUsername = (TextView) findViewById(R.id.name);
        eventtitle = (EditText) findViewById(R.id.eventtitle);

        //String usernameFromIntent = getIntent().getStringExtra("USERNAME");
        //myUsername.setText(usernameFromIntent);
        myUsername.setText(this.getIntent().getStringExtra("USERNAME"));

    }
}

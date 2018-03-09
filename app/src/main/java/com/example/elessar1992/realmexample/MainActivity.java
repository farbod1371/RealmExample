package com.example.elessar1992.realmexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elessar1992.realmexample.Model.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView myUsername;
    private EditText eventtitle;
    private TextView display;
    private Realm myrealm;
    private Button qSave;
    private Button qShow;
    public static final String Tag = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myUsername = (TextView) findViewById(R.id.name);
        eventtitle = (EditText) findViewById(R.id.eventtitle);
        display = (TextView) findViewById(R.id.display);
        qSave = (Button) findViewById(R.id.qSave);
        qShow = (Button) findViewById(R.id.qShow);
        myrealm = Realm.getDefaultInstance();
        //String usernameFromIntent = getIntent().getStringExtra("USERNAME");
        //myUsername.setText(usernameFromIntent);
        myUsername.setText(this.getIntent().getStringExtra("USERNAME"));
        qSave.setOnClickListener(this);
        qShow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.qSave:
                if(checkEvent(myUsername.getText().toString(), eventtitle.getText().toString()))
                {
                    EsaveData();
                    Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Data is Already There", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.qShow:
                Intent showAllquery = new Intent(getApplicationContext(), showEvent.class);
                startActivity(showAllquery);
                //readData();
                break;
        }
    }

    public void EsaveData()
    {
        myrealm.executeTransactionAsync(new Realm.Transaction()
        {
            @Override
            public void execute(Realm bgrealm) {
                User user = bgrealm.createObject(User.class);
                //DB db = bgrealm.createObject(DB.class);
                user.setUsername(myUsername.getText().toString());
                user.setEventTitle(eventtitle.getText().toString());
                //db.setMytitle(eventtitle.getText().toString());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(Tag, "onSuccess");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(Tag, "onError");
            }
        });
    }

    public boolean checkEvent(String username, String eventitle)
    {
        RealmResults<User> users = myrealm.where(User.class).findAll();
        for(User myusers:users)
        {
            if(username.equals(myusers.getUsername()) && eventitle.equals(myusers.getEventTitle()))
            {
                return false;
            }
        }
        return true;
    }



    public void readData()
    {

        RealmResults<User> users = myrealm.where(User.class).findAll();
        String data = "";
        display.setText("");
        for (User user:users)
        {
            try
            {
                Log.d(Tag, "read data");
                data = data + user.toString();
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        display.setText(data);
    }


}


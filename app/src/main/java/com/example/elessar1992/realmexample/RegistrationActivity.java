package com.example.elessar1992.realmexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elessar1992.realmexample.Model.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by elessar1992 on 2/28/18.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener
{
    private final AppCompatActivity activity = RegistrationActivity.this;
    public static final String Tag = RegistrationActivity.class.getName();
    private NestedScrollView nestedScrollView;
    private EditText name;
    private EditText lastname;
    private EditText username;
    private EditText email;
    private EditText password;
    private Button register;
    private Button goLogin;
    private Button delete;
    private TextView display;

    private Realm myrealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews()
    {
        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        goLogin = (Button) findViewById(R.id.gologin);
        delete = (Button) findViewById(R.id.delete);
        display = (TextView) findViewById(R.id.display);

    }

    private void initListeners()
    {
        goLogin.setOnClickListener(this);
        register.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    private void initObjects()
    {
        myrealm = Realm.getDefaultInstance();
        //myrealm.beginTransaction();
        //myrealm.deleteAll();
        //myrealm.commitTransaction();

    }




    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

            case R.id.register:
                saveData();
                Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
                //readData();
                break;

            case R.id.gologin:
                Intent showAllUsers = new Intent(getApplicationContext(), ShowAllUserActivity.class);
                startActivity(showAllUsers);
                break;

            case R.id.delete:
                deleteAll();
                Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();


        }

    }

    public void saveData()
    {
        myrealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                User user = bgrealm.createObject(User.class);
                //user.setFirstname(user.getFirstname());
                user.setFirstname(name.getText().toString().trim());
                user.setLastname(lastname.getText().toString().trim());
                user.setUsername(username.getText().toString().trim());
                user.setEmail(email.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
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

    public void deleteAll()
    {
        RealmResults<User> users = myrealm.where(User.class).findAll();
        myrealm.beginTransaction();
        users.deleteAllFromRealm();
        myrealm.commitTransaction();
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


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}

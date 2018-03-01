package com.example.elessar1992.realmexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.elessar1992.realmexample.Model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by elessar1992 on 2/28/18.
 */

public class ShowAllUserActivity extends AppCompatActivity {
    private AppCompatActivity activity = ShowAllUserActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private ShowAllUserRecycler showAllUserRecycler;
    private RegistrationActivity myregisterActivity;
    Realm myrealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showallusers);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects()
    {
        listUsers = new ArrayList<>();
        showAllUserRecycler = new ShowAllUserRecycler(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(showAllUserRecycler);

        myrealm = Realm.getDefaultInstance();
        myrealm.beginTransaction();
        myrealm.deleteAll();
        myrealm.commitTransaction();



        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
        RealmResults<User> listusers = myrealm.where(User.class).findAll();

    }
}

    /**
     * This method is to fetch all user records from SQLite
     */


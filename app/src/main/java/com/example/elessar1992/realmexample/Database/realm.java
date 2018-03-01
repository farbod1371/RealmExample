package com.example.elessar1992.realmexample.Database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.elessar1992.realmexample.Model.User;
import com.example.elessar1992.realmexample.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by elessar1992 on 2/28/18.
 */

public class realm extends AppCompatActivity
{
    public static final String Tag = realm.class.getName();
    Realm myrealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myrealm = Realm.getDefaultInstance();
        myrealm.beginTransaction();
        myrealm.deleteAll();
        myrealm.commitTransaction();
    }

    private void saveData()
    {
        myrealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                User user = bgrealm.createObject(User.class);
                user.setFirstname(user.getFirstname());
                user.setLastname(user.getLastname());
                user.setUsername(user.getUsername());
                user.setEmail(user.getEmail());
                user.setPassword(user.getPassword());
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

    private void readData()
    {
        RealmResults<User> users = myrealm.where(User.class).findAll();
    }
}

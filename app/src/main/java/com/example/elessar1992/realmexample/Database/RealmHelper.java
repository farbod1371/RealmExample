package com.example.elessar1992.realmexample.Database;

import com.example.elessar1992.realmexample.Model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by elessar1992 on 3/1/18.
 */

public class RealmHelper
{
    Realm realm;

    public RealmHelper(Realm realm)
    {
        this.realm = realm;
    }

    //WRITE
    public void save(final User user)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                User u=realm.copyToRealm(user);

            }
        });
    }

    public List<User> retrieve()
    {
        ArrayList<String> myUsername=new ArrayList<>();
        ArrayList<String> myEmail=new ArrayList<>();
        ArrayList<String> myPassword=new ArrayList<>();
        List<User> userList = new ArrayList<>();
        RealmResults<User> myusers=realm.where(User.class).findAll();

        for(User user:myusers)
        {
            //User user = new User();
            //userList.add(user.getUsername());
            user.getUsername();
            user.getEmail();
            user.getPassword();
            userList.add(user);
        }

        return userList;
    }
}

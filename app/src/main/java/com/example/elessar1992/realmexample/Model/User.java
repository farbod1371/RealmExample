package com.example.elessar1992.realmexample.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by elessar1992 on 2/28/18.
 */

@RealmClass
public class User extends RealmObject
{
    public int id;
    public String firstname;
    public String lastname;
    public String username;
    public String email;
    public String password;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String name)
    {
        this.firstname = name;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String name)
    {
        this.lastname = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}


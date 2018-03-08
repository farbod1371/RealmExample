package com.example.elessar1992.realmexample.Model;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by elessar1992 on 3/6/18.
 */
@RealmClass
public class DB extends RealmObject
{
    public String mytitle;

    public String getMytitle()
    {
        return mytitle;
    }

    public void setMytitle(String mytitle) {
        this.mytitle = mytitle;
    }
}

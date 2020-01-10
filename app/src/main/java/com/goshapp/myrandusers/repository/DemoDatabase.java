package com.goshapp.myrandusers.repository;

/**
 * Created by rikmen00@gmail.com on 10.01.2020.
 */

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.goshapp.myrandusers.data.User;
import com.goshapp.myrandusers.data.ProductDao;

@Database(entities = {User.class}, version = DemoDatabase.VERSION)
public abstract class DemoDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract ProductDao getProductDao();

}
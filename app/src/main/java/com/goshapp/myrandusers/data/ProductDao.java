package com.goshapp.myrandusers.data;

/**
 * Created by rikmen00@gmail.com on 10.01.2020.
 */

import android.arch.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM User WHERE id=:id")
    LiveData<User> findById(int id);

    @Query("SELECT * FROM User")
    LiveData<ArrayList <User>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Delete
    int delete(User user);

}
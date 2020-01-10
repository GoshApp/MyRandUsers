package com.goshapp.myrandusers.repository;

/**
 * Created by rikmen00@gmail.com on 10.01.2020.
 */

import android.arch.lifecycle.LiveData;

import com.goshapp.myrandusers.data.User;

import java.util.List;


public interface ProductRepository {

    LiveData<User> findById(int id);

    LiveData<List<User>> findAll();

    long insert(User user);

    int delete(User user);

}

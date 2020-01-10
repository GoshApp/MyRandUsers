package com.goshapp.myrandusers.repository;

/**
 * Created by rikmen00@gmail.com on 10.01.2020.
 */

import android.arch.lifecycle.LiveData;

import com.goshapp.myrandusers.data.User;
import com.goshapp.myrandusers.data.ProductDao;

import java.util.List;

import javax.inject.Inject;

public class ProductDataSource implements ProductRepository {

    private ProductDao productDao;

    @Inject
    public ProductDataSource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public LiveData<User> findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public LiveData<List<User>> findAll() {
        return productDao.findAll();
    }

    @Override
    public long insert(User user) {
        return productDao.insert(user);
    }

    @Override
    public int delete(User user) {
        return productDao.delete(user);
    }
}
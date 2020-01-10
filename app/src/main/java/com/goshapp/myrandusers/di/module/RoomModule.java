package com.goshapp.myrandusers.di.module;

import android.app.Application;

import androidx.room.Room;

import com.goshapp.myrandusers.data.ProductDao;
import com.goshapp.myrandusers.di.scopes.ApplicationScope;
import com.goshapp.myrandusers.repository.DemoDatabase;
import com.goshapp.myrandusers.repository.ProductDataSource;
import com.goshapp.myrandusers.repository.ProductRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rikmen00@gmail.com on 10.01.2020.
 */

@Module
public class RoomModule {

    private DemoDatabase demoDatabase;

    public RoomModule(Application mApplication) {
        demoDatabase = Room.databaseBuilder(mApplication, DemoDatabase.class, "demo-db").build();
    }

    @ApplicationScope
    @Provides
    DemoDatabase providesRoomDatabase() {
        return demoDatabase;
    }

    @ApplicationScope
    @Provides
    ProductDao providesProductDao(DemoDatabase demoDatabase) {
        return demoDatabase.getProductDao();
    }

    @ApplicationScope
    @Provides
    ProductRepository productRepository(ProductDao productDao) {
        return new ProductDataSource(productDao);
    }

}
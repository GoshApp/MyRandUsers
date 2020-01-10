package com.goshapp.myrandusers.di.component;

import android.content.Context;

import com.goshapp.myrandusers.MyApplication;
import com.goshapp.myrandusers.data.ProductDao;
import com.goshapp.myrandusers.di.module.ContextModule;
import com.goshapp.myrandusers.di.module.RetrofitModule;
import com.goshapp.myrandusers.di.module.RoomModule;
import com.goshapp.myrandusers.di.qualifier.ApplicationContext;
import com.goshapp.myrandusers.di.scopes.ApplicationScope;
import com.goshapp.myrandusers.repository.DemoDatabase;
import com.goshapp.myrandusers.repository.ProductRepository;
import com.goshapp.myrandusers.retrofit.APIInterface;

import dagger.Component;
@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class, RoomModule.class})

public interface ApplicationComponent {

    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    ProductDao productDao();

    DemoDatabase demoDatabase();

    ProductRepository productRepository();

    public void injectApplication(MyApplication myApplication);
}

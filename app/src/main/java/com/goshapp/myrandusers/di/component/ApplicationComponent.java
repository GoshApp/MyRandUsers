package com.goshapp.myrandusers.di.component;

import android.content.Context;

import com.goshapp.myrandusers.MyApplication;
import com.goshapp.myrandusers.di.module.ContextModule;
import com.goshapp.myrandusers.di.module.RetrofitModule;
import com.goshapp.myrandusers.di.qualifier.ApplicationContext;
import com.goshapp.myrandusers.di.scopes.ApplicationScope;
import com.goshapp.myrandusers.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication myApplication);
}

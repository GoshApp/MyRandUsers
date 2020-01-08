package com.goshapp.myrandusers;

import android.app.Activity;
import android.app.Application;

import com.goshapp.myrandusers.di.component.ApplicationComponent;
import com.goshapp.myrandusers.di.component.DaggerApplicationComponent;
import com.goshapp.myrandusers.di.module.ContextModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}


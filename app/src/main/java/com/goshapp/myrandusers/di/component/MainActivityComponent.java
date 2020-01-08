package com.goshapp.myrandusers.di.component;

import android.content.Context;

import com.goshapp.myrandusers.di.module.AdapterModule;
import com.goshapp.myrandusers.di.qualifier.ActivityContext;
import com.goshapp.myrandusers.di.scopes.ActivityScope;
import com.goshapp.myrandusers.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();


    void injectMainActivity(MainActivity mainActivity);
}

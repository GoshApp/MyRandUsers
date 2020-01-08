package com.goshapp.myrandusers.di.component;

import com.goshapp.myrandusers.di.scopes.ActivityScope;
import com.goshapp.myrandusers.ui.DetailActivity;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DetailActivityComponent {

    void inject(DetailActivity detailActivity);
}

package com.goshapp.myrandusers.di.module;

import android.content.Context;

import com.goshapp.myrandusers.di.qualifier.ApplicationContext;
import com.goshapp.myrandusers.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}

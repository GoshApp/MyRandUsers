package com.goshapp.myrandusers.di.module;


import com.goshapp.myrandusers.adapter.RecyclerViewAdapter;
import com.goshapp.myrandusers.di.scopes.ActivityScope;
import com.goshapp.myrandusers.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getRandomUser(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}

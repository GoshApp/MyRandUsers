package com.goshapp.myrandusers.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.goshapp.myrandusers.MyApplication;
import com.goshapp.myrandusers.R;
import com.goshapp.myrandusers.adapter.RecyclerViewAdapter;
import com.goshapp.myrandusers.data.ProductDao;
import com.goshapp.myrandusers.di.component.ApplicationComponent;
import com.goshapp.myrandusers.di.component.DaggerMainActivityComponent;
import com.goshapp.myrandusers.di.component.MainActivityComponent;
import com.goshapp.myrandusers.di.module.MainActivityContextModule;
import com.goshapp.myrandusers.di.qualifier.ActivityContext;
import com.goshapp.myrandusers.di.qualifier.ApplicationContext;
import com.goshapp.myrandusers.helpers.CheckConnection;
import com.goshapp.myrandusers.pojo.RandomUser;
import com.goshapp.myrandusers.pojo.Result;
import com.goshapp.myrandusers.repository.DemoDatabase;
import com.goshapp.myrandusers.repository.ProductRepository;
import com.goshapp.myrandusers.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener, SwipeRefreshLayout.OnRefreshListener {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private List<RandomUser> commentList = new ArrayList <>();
    @BindString(R.string.error) String txtError;
    @BindString(R.string.connection) String txtConnection;
    private MainActivityComponent mainActivityComponent;
    private ApplicationComponent applicationComponent;
    private CheckConnection checkConnection;

    @Inject
    public DemoDatabase demoDatabase;

    @Inject
    public ProductDao productDao;

    @Inject
    public ProductRepository productRepository;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    void gettingUsers(){
        if(isInternet()){
            compositeDisposable.add(
                    apiInterface.getUser()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(randomUser -> {
                                        populateRecyclerView(randomUser.getResults());
                                        refreshLayout.setRefreshing(false);
                                    },
                                    throwable -> {
                                        Timber.e(txtError + throwable);
                                        refreshLayout.setRefreshing(false);
                                    }));
        }else {
            Toast.makeText(getApplicationContext(), txtConnection,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_list);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorScheme(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        checkConnection = new CheckConnection();
        applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        onRefresh();
    }

    private void populateRecyclerView(List<Result> response) {
        recyclerViewAdapter.setData(response);
        recyclerViewAdapter.notifyDataSetChanged();
    }


    private boolean isInternet() {
        if (checkConnection.isOnline(getApplicationContext())) {
            return true;
        }else {
            checkConnection.makeToastConnection(this);
            return false;
        }
    }


    @Override
    public void launchIntent(String userName) {
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("url", userName));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        try {
            if(!refreshLayout.isRefreshing()){
                refreshLayout.setRefreshing(true);
            }
            refreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isInternet()){
                        gettingUsers();
                    } else {
                        refreshLayout.setRefreshing(false);
                    }
                }
            }, 1000);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
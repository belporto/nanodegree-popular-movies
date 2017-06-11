package com.porto.isabel.popularmoviesstage1.screens.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.porto.isabel.popularmoviesstage1.AppApplication;
import com.porto.isabel.popularmoviesstage1.R;
import com.porto.isabel.popularmoviesstage1.dagger.AppComponent;
import com.porto.isabel.popularmoviesstage1.screens.home.core.DaggerHomeComponent;
import com.porto.isabel.popularmoviesstage1.screens.home.dagger.HomeModule;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.ViewContract {

    @Inject
    HomeContract.PresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerHomeComponent.builder().appComponent(appComponent).homeModule(
                new HomeModule(this)).build().inject(this);

        setContentView(R.layout.activity_home);
        mPresenter.onCreate();
    }


}

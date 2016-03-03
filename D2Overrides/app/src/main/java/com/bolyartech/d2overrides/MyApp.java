package com.bolyartech.d2overrides;

import android.app.Application;

import com.bolyartech.d2overrides.dagger.DaggerMyDaggerComponent;
import com.bolyartech.d2overrides.dagger.HttpModule;
import com.bolyartech.d2overrides.dagger.MyDaggerComponent;

import okhttp3.OkHttpClient;


public class MyApp extends Application {
    private MyDaggerComponent mInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }


    /**
     * This method must contain only the component/injector initialization and call to {onInjectorInitialized()}.
     * Put any other logic in {onInjectorInitialized()} because {initInjector()} will be overridden in the test
     * so you will miss any additional initializations if you put them here.
     */
    protected void initInjector() {
        // we build with 'real' OkHttpClient
        mInjector = DaggerMyDaggerComponent.builder().httpModule(new HttpModule(new OkHttpClient())).build();

        onInjectorInitialized(mInjector);
    }


    private void onInjectorInitialized(MyDaggerComponent inj) {
        // here we don't have any injects in the app itself but in real applications
        // you may/will have
        inj.inject(this);

        // here is the place for other logic that depends that app fields are injected (if any)
    }


    public MyDaggerComponent getInjector() {
        return mInjector;
    }


    /*
     * Will be used (only) by unit tests to provide their own injector with overridden dependencies
     */
    public void externalInjectorInitialization(MyDaggerComponent injector) {
        mInjector = injector;

        onInjectorInitialized(injector);
    }
}

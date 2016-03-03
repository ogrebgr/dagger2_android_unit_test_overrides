package com.bolyartech.d2overrides.dagger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class HttpModule {
    private final OkHttpClient mOkHttpClient;


    public HttpModule(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }


    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClient() {
        return mOkHttpClient;
    }
}

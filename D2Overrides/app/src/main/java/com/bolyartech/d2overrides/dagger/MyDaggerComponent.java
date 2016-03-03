package com.bolyartech.d2overrides.dagger;

import com.bolyartech.d2overrides.Act_Main;
import com.bolyartech.d2overrides.MyApp;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {HttpModule.class})
@Singleton
public interface MyDaggerComponent {
    void inject(MyApp act);
    void inject(Act_Main act);
}

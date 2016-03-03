package com.bolyartech.d2overrides.utils;

import com.bolyartech.d2overrides.MyApp;


public class MyTestApp extends MyApp {

    /*
    Overriding the initInjector() in order tests to be able to provide their own injector
    (if we don't override it with empty body tests will still provide their own injector
    but we will have 2 injector initializations: one in MyApp.initInjector() and second
    in the test itself)
     */
    @Override
    protected void initInjector() {

    }
}

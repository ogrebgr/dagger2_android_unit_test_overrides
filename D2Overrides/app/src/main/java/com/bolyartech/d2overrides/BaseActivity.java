package com.bolyartech.d2overrides;

import android.app.Activity;


abstract public class BaseActivity extends Activity {
    public MyApp getApp() {
        return (MyApp) getApplication();
    }
}

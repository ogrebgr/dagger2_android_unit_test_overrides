package com.bolyartech.d2overrides;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Act_Main extends BaseActivity {
    private TextView mTvResult;

    @Inject
    OkHttpClient mOkHttpClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApp().getInjector().inject(this);

        setContentView(R.layout.act__main);

        initViews(getWindow().getDecorView());
    }


    private void initViews(View view) {
        Button btnExecute = (Button) view.findViewById(R.id.btn_execute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpTask t = new HttpTask();
                t.execute();
            }
        });

        mTvResult = (TextView) view.findViewById(R.id.tv_result);
    }



    private class HttpTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            Request request = new Request.Builder()
                    .url("https://www.google.bg/")
                    .build();

            String ret = null;
            try {
                Response response = mOkHttpClient.newCall(request).execute();
                ret = response.body().string();
            } catch (IOException e) {
                Log.e("some tag", e.getMessage());
            }

            return ret;
        }


        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);

            if (str != null) {
                mTvResult.setText(str.substring(0, Math.min(str.length(), 400)));
            } else {
                mTvResult.setText("error");
            }
        }
    }

}

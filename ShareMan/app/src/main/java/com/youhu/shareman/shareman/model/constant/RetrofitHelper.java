package com.youhu.shareman.shareman.model.constant;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.youhu.shareman.shareman.model.Api.RetrofitService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Touch on 2017/9/15.
 */

public class RetrofitHelper {
    private Context mCntext;
    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context mContext) {
        mCntext = mContext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder().baseUrl(AppConfig.BASE_URL).client(client).addConverterFactory(factory).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    public RetrofitService getServer() {
        return mRetrofit.create(RetrofitService.class);
    }

}

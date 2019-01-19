package com.example.gongweuc.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtiles {
    private Handler handler=new Handler();
    private OkHttpClient okHttpClient;
    private static OkHttpUtiles mIncvxcxm;
    public OkHttpUtiles(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public void doPost(String apiUer, HashMap<String,String>params, final OkHttpCallback okHttpCallback){
        FormBody.Builder formBody = new FormBody.Builder();
        if (params!=null){
            for (Map.Entry<String,String>p:params.entrySet()){
                formBody.add(p.getKey(),p.getValue());
            }
        }
        Request request = new Request.Builder().url(apiUer).post(formBody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okHttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.onFaliUre("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                int code = response.code();
                if (200==code){
                    if (okHttpCallback!=null){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okHttpCallback.onSuccess(result);
                            }
                        });
                    }
                }
            }
        });
    }
    public static OkHttpUtiles getmIncvxcxm(){
        if (mIncvxcxm==null){
            synchronized (OkHttpUtiles.class){
                if (mIncvxcxm==null){
                    mIncvxcxm=new OkHttpUtiles();
                }
            }
        }
        return mIncvxcxm;
    }
    public void dsscxvzzcxz(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}

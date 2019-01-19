package com.example.gongweuc.model;

import android.text.TextUtils;

import com.example.gongweuc.api.UserApi;
import com.example.gongweuc.contract.IShowContract;

import com.example.gongweuc.net.OkHttpCallback;
import com.example.gongweuc.net.OkHttpUtiles;
import com.example.gongweuc.net.RequestCallback;


import java.util.HashMap;

public class ShowModel implements IShowContract.IShowModel {
    @Override
    public void showmodel(HashMap<String, String> params, final RequestCallback callback) {
        OkHttpUtiles.getmIncvxcxm().doPost(UserApi.USER_SHOW, params, new OkHttpCallback() {
            @Override
            public void onFaliUre(String msg) {
                if (callback!=null){
                    callback.onFailUre(msg);
                }
            }

            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    if (callback!=null){
                        callback.onSuccess(result);
                    }
                }
            }
        });
    }

    @Override
    public void usermodel(HashMap<String, String> params, final RequestCallback callback) {
        OkHttpUtiles.getmIncvxcxm().doPost(UserApi.USER_USER, params, new OkHttpCallback() {
            @Override
            public void onFaliUre(String msg) {
                if (callback!=null){
                    callback.onFailUre(msg);
                }
            }

            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    if (callback!=null){
                        callback.onSuccess(result);
                    }
                }
            }
        });
    }

    @Override
    public void addshop(HashMap<String, String> params, final RequestCallback callback) {
        OkHttpUtiles.getmIncvxcxm().doPost(UserApi.USER_ADDSHOP, params, new OkHttpCallback() {
            @Override
            public void onFaliUre(String msg) {
                if (callback!=null){
                    callback.onFailUre(msg);
                }
            }

            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    if (callback!=null){
                        callback.onSuccess(result);
                    }
                }
            }
        });
    }
}

package com.example.gongweuc.contract;

import com.example.gongweuc.net.RequestCallback;

import java.util.HashMap;

public interface IShowContract {
    public abstract class IShowPreserve{
        public abstract void showpreserve(HashMap<String,String>params);
        public abstract void userpreserve(HashMap<String,String>params);
        public abstract void addshop(HashMap<String,String>params);
    }
    interface IShowModel{
        void showmodel(HashMap<String,String>params, RequestCallback callback);
        void usermodel(HashMap<String,String>params, RequestCallback callback);
        void addshop(HashMap<String,String>params, RequestCallback callback);
    }
    interface IShowView{
        void onFaliUre(String msg);
        void onSuccess(String result);
        void onSuccessAddShop(String result);
    }
}

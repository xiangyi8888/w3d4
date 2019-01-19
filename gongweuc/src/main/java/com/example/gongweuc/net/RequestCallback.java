package com.example.gongweuc.net;

import com.example.gongweuc.entity.Show;

public interface RequestCallback {
    void onFailUre(String msg);
    void onSuccess(String result);


}

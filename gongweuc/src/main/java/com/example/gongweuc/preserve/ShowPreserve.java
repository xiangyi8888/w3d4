package com.example.gongweuc.preserve;

import com.example.gongweuc.contract.IShowContract;
import com.example.gongweuc.model.ShowModel;
import com.example.gongweuc.net.RequestCallback;

import java.util.HashMap;

public class ShowPreserve extends IShowContract.IShowPreserve {
    private ShowModel showModel;
    private IShowContract.IShowView iShowView;

    public ShowPreserve( IShowContract.IShowView iShowView) {
        this.showModel = new ShowModel();
        this.iShowView = iShowView;
    }

    @Override
    public void showpreserve(HashMap<String, String> params) {
        if (showModel!=null){
            showModel.showmodel(params, new RequestCallback() {
                @Override
                public void onFailUre(String msg) {
                    if (iShowView!=null){
                        iShowView.onFaliUre(msg);
                    }
                }

                @Override
                public void onSuccess(String result) {
                    if (iShowView!=null){
                        iShowView.onSuccess(result);
                    }
                }
            });
        }
    }

    @Override
    public void userpreserve(HashMap<String, String> params) {
        if (showModel!=null){
            showModel.usermodel(params, new RequestCallback() {
                @Override
                public void onFailUre(String msg) {
                    if (iShowView!=null){
                        iShowView.onFaliUre(msg);
                    }
                }

                @Override
                public void onSuccess(String result) {
                    if (iShowView!=null){
                        iShowView.onSuccess(result);
                    }
                }
            });
        }
    }

    @Override
    public void addshop(HashMap<String, String> params) {
        if (showModel!=null){
            showModel.addshop(params, new RequestCallback() {
                @Override
                public void onFailUre(String msg) {
                    if (iShowView!=null){
                        iShowView.onFaliUre(msg);
                    }
                }

                @Override
                public void onSuccess(String result) {
                    if (iShowView!=null){
                        iShowView.onSuccess(result);
                    }
                }
            });
        }
    }

    public void cvxcxm(){
        if (iShowView!=null){
            iShowView=null;
        }
    }
}

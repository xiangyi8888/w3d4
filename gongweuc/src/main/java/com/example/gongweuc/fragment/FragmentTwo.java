package com.example.gongweuc.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.gongweuc.R;
import com.example.gongweuc.adapter.UserYiAdapter;
import com.example.gongweuc.callback.CartUlCallback;
import com.example.gongweuc.contract.IShowContract;
import com.example.gongweuc.entity.User;
import com.example.gongweuc.preserve.ShowPreserve;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentTwo extends BaseFragment implements IShowContract.IShowView,CartUlCallback {

    private XRecyclerView xr;
    private TextView zong;
    private ShowPreserve showPreserve;
    private UserYiAdapter userYiAdapter;
    private List<User.DataBase> car;
    private User user;
    private CheckBox checkbox;

    @Override
    protected int getLayouView() {
        return R.layout.fragmenttwo;
    }

    @Override
    protected void initView(View view) {
        xr = (XRecyclerView)view.findViewById(R.id.xr);
        xr.setLayoutManager(new LinearLayoutManager(getActivity()));
        xr.setPullRefreshEnabled(true);
        xr.setLoadingMoreEnabled(false);
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                reqData();
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });
        zong = (TextView) view.findViewById(R.id.zong);
        checkbox=(CheckBox)view.findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (User.DataBase cart : car) {
                        cart.checkbox=true;
                        for (User.DataBase.Product product : cart.list) {
                            product.isproductcheckbox=true;
                        }
                    }
                }else {
                    for (User.DataBase cart : car) {
                        cart.checkbox=false;
                        for (User.DataBase.Product product : cart.list) {
                            product.isproductcheckbox=false;
                        }
                    }
                }
                userYiAdapter.notifyDataSetChanged();
                getTotalPrice();
            }
        });
        userYiAdapter = new UserYiAdapter(getActivity(),this);
        xr.setAdapter(userYiAdapter);
    }

    private void reqData() {
        showPreserve = new ShowPreserve(this);
        HashMap<String,String>params=new HashMap<>();
        params.put("uid","600");
        showPreserve.userpreserve(params);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onFaliUre(String msg) {

    }

    @Override
    public void onSuccess(String result) {
        user = new Gson().fromJson(result, User.class);
        car = user.data;
        for (User.DataBase dataBase : car) {
            for (User.DataBase.Product product : dataBase.list) {
                product.productNum=1;
            }
        }
        userYiAdapter.setList(user.data);

    }

    @Override
    public void onSuccessAddShop(String result) { }
    private void getTotalPrice(){
        double totalprice=0;
        for (User.DataBase dataBase : car) {
            for (User.DataBase.Product product : dataBase.list) {
                if (product.isproductcheckbox){
                    totalprice+=product.price*product.productNum;
                }
            }
        }
        zong.setText("￥:"+totalprice);
    }

    @Override
    public void notifyCart() {
        getTotalPrice();
    }
}

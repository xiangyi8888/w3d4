package com.example.gongweuc.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gongweuc.R;
import com.example.gongweuc.adapter.ShowAdapter;
import com.example.gongweuc.contract.IShowContract;
import com.example.gongweuc.entity.AddShop;
import com.example.gongweuc.entity.Show;
import com.example.gongweuc.preserve.ShowPreserve;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class FragmentOne extends BaseFragment implements IShowContract.IShowView {

    private EditText title;
    private Button sos;
    private int page=1;
    private XRecyclerView xr;
    private ShowPreserve showPreserve;
    private ShowAdapter showAdapter;
    private Show show;


    @Override
    protected int getLayouView() {
        return R.layout.fragmentone;
    }

    @Override
    protected void initView(View view) {
        title = (EditText) view.findViewById(R.id.title);
        sos = (Button) view.findViewById(R.id.sos);
        xr = (XRecyclerView)view.findViewById(R.id.xr);
        xr.setLayoutManager(new LinearLayoutManager(getActivity()));
        showAdapter = new ShowAdapter(getActivity());
        showPreserve = new ShowPreserve(this);
        showAdapter.setGetpidcllback(new ShowAdapter.getpidCllback() {
            @Override
            public void getpid(int pid) {

                HashMap<String,String> params = new HashMap<>();
                params.put("uid","600");
                params.put("pid",pid+"");
                showPreserve.addshop(params);

            }
        });

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = title.getText().toString();
                HashMap<String, String> params = new HashMap<>();
                params.put("keywords",name);
                params.put("page",page+"");
                showPreserve.showpreserve(params);
            }
        });
        xr.setAdapter(showAdapter);
        xr.setPullRefreshEnabled(true);
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                init();
                xr.refreshComplete();
                showAdapter.setShow(show.getData());
                showAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                page++;
                init();
                xr.refreshComplete();
                showAdapter.addShow(show.getData());
                showAdapter.notifyDataSetChanged();
            }
            public void init() {
                String name = title.getText().toString();
                HashMap<String, String> params = new HashMap<>();
                params.put("keywords",name);
                params.put("page",page+"");
                showPreserve.showpreserve(params);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onFaliUre(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String result) {
        show = new Gson().fromJson(result, Show.class);
        Toast.makeText(getActivity(), show.getMsg(),Toast.LENGTH_SHORT).show();
        showAdapter.setShow(show.getData());
    }

    @Override
    public void onSuccessAddShop(String result) {
        AddShop addShop = new Gson().fromJson(result, AddShop.class);
        Toast.makeText(getActivity(), addShop.getMsg(),Toast.LENGTH_SHORT).show();

    }



}

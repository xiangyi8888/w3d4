package com.example.gongweuc.adapter;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gongweuc.R;
import com.example.gongweuc.callback.CartCallback;
import com.example.gongweuc.callback.CartUlCallback;
import com.example.gongweuc.entity.User;
import com.example.gongweuc.fragment.FragmentTwo;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserYiAdapter extends RecyclerView.Adapter<UserYiAdapter.ViewHolder>implements CartCallback {
    private Context context;
    private List<User.DataBase> list;
    private CartUlCallback cartUlCallback;


    public UserYiAdapter(Context context,CartUlCallback cartUlCallback) {
        this.context = context;
        this.list = new ArrayList<>();
        this.cartUlCallback=cartUlCallback;
    }

    public void setList(List<User.DataBase> list) {
        if (list!=null){
            this.list = list;
        }
        notifyDataSetChanged();
    }
    public void addList(List<User.DataBase> list) {
        if (list!=null){
            list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.gwc_yiji,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        for (User.DataBase.Product product : list.get(i).list) {
            product.pos=i;
        }
        double price1 = 0;
        for (User.DataBase.Product product : list.get(i).list) {
            price1+=product.price*product.productNum;

        }
        viewHolder.xiao.setText(price1+"");
        User.DataBase dataBase = list.get(i);
        viewHolder.name.setText(dataBase.sellerName);
        viewHolder.xr.setLayoutManager(new LinearLayoutManager(context));
        UserErAdapter userErAdapter = new UserErAdapter(context, list.get(i).list);
        userErAdapter.setCartCallback(this);
        viewHolder.xr.setAdapter(userErAdapter);
        viewHolder.checkbox.setChecked(list.get(i).checkbox);
        if (dataBase.checkbox){
            viewHolder.checkbox.setChecked(true);
        }else {
            viewHolder.checkbox.setChecked(false);
        }
        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).checkbox=viewHolder.checkbox.isChecked();
                for (User.DataBase.Product product : list.get(i).list) {
                    product.isproductcheckbox=list.get(i).checkbox;
                }
                notifyDataSetChanged();
                if (cartUlCallback!=null){
                    cartUlCallback.notifyCart();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void notifyCartItem(boolean isChecked, int postion) {
        list.get(postion).checkbox = isChecked;
        notifyDataSetChanged();
        if (cartUlCallback!=null){
            cartUlCallback.notifyCart();
        }
    }

    @Override
    public void notifyNum() {
        if (cartUlCallback!=null){
            cartUlCallback.notifyCart();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,xiao;
        private XRecyclerView xr;
        private CheckBox checkbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name=itemView.findViewById(R.id.name);
            this.xiao=itemView.findViewById(R.id.xiao);
            this.xr=itemView.findViewById(R.id.xr);
            this.checkbox=itemView.findViewById(R.id.checkbox);
        }
    }
}

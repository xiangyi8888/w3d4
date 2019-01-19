package com.example.gongweuc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gongweuc.R;
import com.example.gongweuc.callback.CartCallback;
import com.example.gongweuc.entity.User;
import com.example.gongweuc.view.MyView;

import java.util.ArrayList;
import java.util.List;

public class UserErAdapter extends RecyclerView.Adapter<UserErAdapter.ViewHodler>{
    private Context context;
    private List<User.DataBase.Product> user;
    private CartCallback cartCallback;

    public void setCartCallback(CartCallback cartCallback) {
        this.cartCallback = cartCallback;
    }

    public UserErAdapter(Context context, List<User.DataBase.Product> user) {
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.gwc_erji,viewGroup,false);
        ViewHodler viewHodler=new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHodler viewHodler, final int i) {
        User.DataBase.Product product = user.get(i);
        String[] split = product.images.split("!");
        Glide.with(context).load(split[0]).into(viewHodler.img);
        viewHodler.price.setText("￥："+product.price);
        viewHodler.title.setText(product.title);
        viewHodler.myview.setNum(user.get(i).productNum);
        viewHodler.checkbox.setChecked(product.isproductcheckbox);
        viewHodler.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               user.get(i).isproductcheckbox= viewHodler.checkbox.isChecked();
                for (User.DataBase.Product show : user) {
                    if (!show.isproductcheckbox){
                        if (cartCallback!=null){
                            cartCallback.notifyCartItem(false,show.pos);
                        }
                        return;
                    }
                    if (cartCallback!=null){
                        cartCallback.notifyCartItem(true,show.pos);
                    }
                }
            }
        });
        viewHodler.myview.setGetnumcallback(new MyView.getNumcallback() {
            @Override
            public void getnum(int nums) {
                user.get(i).productNum=nums;
                if (cartCallback!=null){
                    cartCallback.notifyNum();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder{
        private TextView title,price;
        private ImageView img;
        private CheckBox checkbox;
        private MyView myview;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            this.checkbox=itemView.findViewById(R.id.checkbox);
            this.img=itemView.findViewById(R.id.img);
            this.myview=itemView.findViewById(R.id.myview);
            this.title=itemView.findViewById(R.id.title);
            this.price=itemView.findViewById(R.id.price);
        }
    }
}

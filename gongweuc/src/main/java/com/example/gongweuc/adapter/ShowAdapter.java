package com.example.gongweuc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gongweuc.R;
import com.example.gongweuc.entity.Show;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder>{
    private Context context;
    private List<Show.DataBean> show;

    public ShowAdapter(Context context) {
        this.context = context;
        this.show = new ArrayList<>();
    }

    public void setShow(List<Show.DataBean> show) {
        if (show!=null){
            this.show = show;
        }
        notifyDataSetChanged();
    }
    public void addShow(List<Show.DataBean> show) {
        if (show!=null){
            show.addAll(show);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.show_shu,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Show.DataBean dataBean = show.get(i);
        String[] split = dataBean.getImages().split("!");
        Glide.with(context).load(split[0]).into(viewHolder.img);
        viewHolder.price.setText("￥："+dataBean.getPrice());
        viewHolder.title.setText(dataBean.getTitle());
        viewHolder.gwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getpidcllback!=null){
                    getpidcllback.getpid(show.get(i).getPid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return show==null?0:show.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,price;
        private ImageView img;
        private Button gwc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gwc = itemView.findViewById(R.id.gwc);
            this.title=itemView.findViewById(R.id.title);
            this.img=itemView.findViewById(R.id.img);
            this.price=itemView.findViewById(R.id.price);
        }
    }

    private getpidCllback getpidcllback;

    public void setGetpidcllback(getpidCllback getpidcllback) {
        this.getpidcllback = getpidcllback;
    }

    public interface getpidCllback{
        void getpid(int pid);
    }

}

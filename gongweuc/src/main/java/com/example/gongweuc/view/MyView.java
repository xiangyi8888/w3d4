package com.example.gongweuc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gongweuc.R;

public class MyView extends LinearLayout {

    private TextView jia;
    private EditText num;
    private TextView jian;
    private int nums=1;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
        View view=LayoutInflater.from(context).inflate(R.layout.zdyview,this);
        jia = view.findViewById(R.id.jia);
        num = view.findViewById(R.id.num);
        jian = view.findViewById(R.id.jian);
        num.setText("1");
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nums=Integer.parseInt(num.getText().toString());
                nums--;
                if (nums==0){
                    nums=1;
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }
                num.setText(nums+"");
                if (getnumcallback!=null){
                    getnumcallback.getnum(nums);
                }
            }
        });
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nums=Integer.parseInt(num.getText().toString());
                nums++;
                num.setText(nums+"");
                if (getnumcallback!=null){
                    getnumcallback.getnum(nums);
                }
            }
        });
    }
    public void setNum(int nums){
        num.setText(nums+"");
    }
    private getNumcallback getnumcallback;

    public void setGetnumcallback(getNumcallback getnumcallback) {
        this.getnumcallback = getnumcallback;
    }

    public interface getNumcallback{
        void getnum(int nums);
    }
}

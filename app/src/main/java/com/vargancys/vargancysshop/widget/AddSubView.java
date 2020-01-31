package com.vargancys.vargancysshop.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.vargancysshop.R;

import java.util.jar.Attributes;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public class AddSubView extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private ImageView iv_add;
    private ImageView iv_sub;
    private TextView tv_value;
    private int value = 1;
    private int minValue;
    private int maxValue;
    public AddSubView(Context context, AttributeSet attrs){
        super(context,attrs);
        View.inflate(context, R.layout.add_sub_view,this);
        mContext = context;
        iv_add = findViewById(R.id.tv_add);
        iv_sub = findViewById(R.id.tv_sub);
        tv_value = findViewById(R.id.tv_text);

        int value = getValue();
        setValue(value);

        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_add:
                addNumber();
                break;
            case R.id.tv_sub:
                subNumber();
                break;
        }
    }

    private void addNumber(){
        if(value<maxValue){
            value ++;
        }
        setValue(value);
        if(onNumberChangeListener != null){
            onNumberChangeListener.onAddChange(value);
        }
        if(value == maxValue){
            iv_add.setClickable(false);
            iv_add.setBackground(mContext.getResources().getDrawable(R.drawable.car_add_clicked));
            iv_sub.setBackground(mContext.getResources().getDrawable(R.drawable.car_sub));
            iv_sub.setClickable(true);
        }
    }

    private void subNumber(){
        if(value>minValue){
            value --;
        }
        setValue(value);
        if(onNumberChangeListener != null){
            onNumberChangeListener.onSubChange(value);
        }
        if(value == minValue-1){
            iv_sub.setBackground(mContext.getResources().getDrawable(R.drawable.car_sub_clicked));
            iv_sub.setClickable(false);
            iv_add.setBackground(mContext.getResources().getDrawable(R.drawable.car_add));
            iv_add.setClickable(true);
        }
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(String.valueOf(value));
    }
    public interface OnNumberChangeListener{
        void onAddChange(int value);
        void onSubChange(int value);
    }

    public OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}





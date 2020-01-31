package com.vargancys.vargancysshop.module.home.car.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.home.car.data.ShoppingCarInfo;
import com.vargancys.vargancysshop.widget.AddSubView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public class ShoppingCarAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ShoppingCarInfo> shoppingCarInfo;
    private CheckBox carCheckButton;
    private TextView carMoney;
    private int shoppingLength;
    public ShoppingCarAdapter(Context context, List<ShoppingCarInfo> shoppingCarInfo,
                              CheckBox carCheckButton,TextView carMoney){
        this.mContext = context;
        this.shoppingCarInfo = shoppingCarInfo;
        this.carCheckButton = carCheckButton;
        this.carMoney = carMoney;
        shoppingLength = shoppingCarInfo.size();
        showTotalPrice();
        checkAll();

        carCheckButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setCheckAll(true);
                }else{
                    setCheckAll(false);
                }

            }
        });
    }

    public void checkAll_none(boolean isCheck){
        if(shoppingCarInfo !=null&& shoppingCarInfo.size() > 0){
            for (int i = 0;i < shoppingLength;i++){
                shoppingCarInfo.get(i).setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    private void setCheckAll(boolean bool){
        for (int i = 0;i < shoppingLength;i++){
            shoppingCarInfo.get(i).setSelected(bool);
            notifyItemChanged(i);
        }
    }

    public void deleteData(){
        if (shoppingCarInfo != null && shoppingCarInfo.size() > 0){
            shoppingLength = shoppingCarInfo.size();
            for (int i = 0;i < shoppingLength;i++){
                ShoppingCarInfo shopping = shoppingCarInfo.get(i);
                if(shopping.isSelected()){
                    shoppingCarInfo.remove(shopping);
                    notifyItemChanged(i);
                    i--;
                }
            }
        }
    }

    public void showTotalPrice(){
        carMoney.setText(String.valueOf(getTotalPrice()));
    }

    private double getTotalPrice(){
        double totalPrice = 0.0;
        if(shoppingCarInfo !=null&& shoppingCarInfo.size() > 0){
            for (int i = 0; i < shoppingLength; i++ ){
                ShoppingCarInfo shopping = shoppingCarInfo.get(i);
                if(shopping.isSelected()){
                    totalPrice = totalPrice +Double.valueOf(shopping.getNumber()) * Double.valueOf(shopping.getNumber());
                }
            }
        }
        return  totalPrice;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ShoppingCarViewHolder(View.inflate(mContext, R.layout.shopping_car_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ShoppingCarViewHolder mHolder = (ShoppingCarViewHolder) holder;
        Glide.with(mContext)
                .load(shoppingCarInfo.get(position).getUrl())
                .into(mHolder.ItemImage);
        mHolder.ItemCheck.setChecked(shoppingCarInfo.get(position).isSelected());
        mHolder.ItemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shoppingCarInfo.get(position).setSelected(!shoppingCarInfo.get(position).isSelected());
                notifyItemChanged(position);
                checkAll();
                showTotalPrice();
            }
        });

        mHolder.ItemTitle.setText(shoppingCarInfo.get(position).getTitle());
        mHolder.ItemMoney.setText(shoppingCarInfo.get(position).getMoney());
        mHolder.ItemAddSub.setValue(shoppingCarInfo.get(position).getNumber());
        mHolder.ItemAddSub.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onAddChange(int value) {
                shoppingCarInfo.get(position).setNumber(value);
                notifyItemChanged(position);
                showTotalPrice();
            }

            @Override
            public void onSubChange(int value) {

            }
        });
    }

    private void checkAll(){
        if(shoppingCarInfo !=null&& shoppingCarInfo.size() > 0){
            int number = 0;
            for (int i = 0;i<shoppingLength;i++){
                if(shoppingCarInfo.get(i).isSelected()){
                    carCheckButton.setChecked(false);
                }else{
                    number++;
                }
            }
            if(number == shoppingLength){
                carCheckButton.setChecked(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return shoppingCarInfo.size();
    }

    class ShoppingCarViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_image)
        ImageView ItemImage;
        @BindView(R.id.item_title)
        TextView ItemTitle;
        @BindView(R.id.item_money)
        TextView ItemMoney;
        @BindView(R.id.item_add_sub)
        AddSubView ItemAddSub;
        @BindView(R.id.item_check)
        CheckBox ItemCheck;
        ShoppingCarViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}

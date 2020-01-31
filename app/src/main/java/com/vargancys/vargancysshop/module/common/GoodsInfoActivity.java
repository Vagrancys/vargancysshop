package com.vargancys.vargancysshop.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle.components.RxActivity;
import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.home.home.data.GoodsBaseInfo;
import com.vargancys.vargancysshop.module.home.home.data.GoodsItem;
import com.vargancys.vargancysshop.utils.Constants;
import com.vargancys.vargancysshop.utils.Https;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsInfoActivity extends RxActivity implements View.OnClickListener {
    @BindView(R.id.goods_back)
    ImageView goodsBack;
    @BindView(R.id.goods_more)
    ImageView goodsMore;
    @BindView(R.id.goods_service)
    TextView goodsService;
    @BindView(R.id.goods_collect)
    TextView goodsCollect;
    @BindView(R.id.goods_car)
    TextView goodsCar;
    @BindView(R.id.goods_join)
    TextView goodsJoin;
    @BindView(R.id.goods_money)
    TextView goodsMoney;

    private int goods_id;
    private GoodsItem goodsItem = new GoodsItem();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        Intent intent = getIntent();
        if(intent !=null){
            goods_id = intent.getIntExtra(Constants.GOODS,0);
        }
        goodsBack.setOnClickListener(this);
        goodsMore.setOnClickListener(this);
        goodsService.setOnClickListener(this);
        goodsCar.setOnClickListener(this);
        goodsCollect.setOnClickListener(this);
        goodsJoin.setOnClickListener(this);
        initLoadData();
    }

    public void initLoadData() {
        Https.getGoodsAPI().getGoodsBaseInfo(goods_id)
                .enqueue(new Callback<GoodsBaseInfo>() {
                    @Override
                    public void onResponse(Call<GoodsBaseInfo> call, Response<GoodsBaseInfo> response) {
                        if(response.body().getState() == 200){
                            goodsItem = response.body().getGoodsItem();
                            initData();
                        }
                    }

                    @Override
                    public void onFailure(Call<GoodsBaseInfo> call, Throwable t) {
                        Toast.makeText(getBaseContext(),"失败!",Toast.LENGTH_LONG).show();
                    }
                });

    }
    public void initData(){
        goodsMoney.setText(goodsItem.getGoods_money());
    }

    public static void launch(Activity activity, int goods) {
        Intent intent = new Intent(activity, GoodsInfoActivity.class);
        intent.putExtra(Constants.GOODS, goods);
        activity.startActivity(intent);
    }

    @OnClick({R.id.goods_back, R.id.goods_more,
            R.id.goods_service, R.id.goods_collect,
            R.id.goods_car, R.id.goods_join})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goods_back:
                finish();
                break;
            case R.id.goods_more:
                initPopupWindow();
                break;
            case R.id.goods_service:
                Toast.makeText(getBaseContext(), "客服", Toast.LENGTH_LONG).show();
                break;
            case R.id.goods_collect:
                Toast.makeText(getBaseContext(), "收藏", Toast.LENGTH_LONG).show();
                break;
            case R.id.goods_car:
                Toast.makeText(getBaseContext(), "购物车", Toast.LENGTH_LONG).show();
                break;
            case R.id.goods_join:
                Toast.makeText(getBaseContext(), "加入购物车", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void initPopupWindow() {
        PopupWindow mPopup = new PopupWindow();
        mPopup.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_goods_more));
        mPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = View.inflate(getBaseContext(), R.layout.popup_goods_more, null);

        TextView GoodsShare = mView.findViewById(R.id.goods_share);
        GoodsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "分享", Toast.LENGTH_LONG).show();
            }
        });

        TextView GoodsSearch = mView.findViewById(R.id.goods_search);
        GoodsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "搜索", Toast.LENGTH_LONG).show();
            }
        });

        TextView GoodsHome = mView.findViewById(R.id.goods_home);
        GoodsHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "首页", Toast.LENGTH_LONG).show();
            }
        });

        mPopup.setContentView(mView);
        mPopup.setAnimationStyle(R.anim.popup_goods_more);
        mPopup.showAsDropDown(goodsMore);
    }
}

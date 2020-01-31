package com.vargancys.vargancysshop.module.home.car.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.base.BaseFragment;
import com.vargancys.vargancysshop.module.home.car.adapter.ShoppingCarAdapter;
import com.vargancys.vargancysshop.module.home.car.data.CarBaseInfo;
import com.vargancys.vargancysshop.module.home.car.data.ShoppingCarInfo;
import com.vargancys.vargancysshop.utils.Https;
import com.vargancys.vargancysshop.utils.Resources;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/25
 * version:1.0
 */
public class CarFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.car_edit)
    TextView carEdit;
    @BindView(R.id.car_check_button)
    CheckBox carCheckButton;
    @BindView(R.id.car_money)
    TextView carMoney;
    @BindView(R.id.car_check_all)
    LinearLayout carCheckAll;
    @BindView(R.id.car_check_delete)
    CheckBox carCheckDelete;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.car_delete)
    LinearLayout carDelete;
    @BindView(R.id.car_data_button)
    TextView carDataButton;
    @BindView(R.id.car_empty)
    LinearLayout carEmpty;
    @BindView(R.id.car_recycler)
    RecyclerView carRecycler;
    @BindView(R.id.car_all)
    LinearLayout carAll;

    private boolean edit_state = false;
    private String user_name;
    private List<ShoppingCarInfo> shoppingCarInfos = new ArrayList<>();
    private ShoppingCarAdapter mAdapter;

    public static CarFragment newInstance() {
        return new CarFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_car;
    }

    @Override
    public void finishCreateView(Bundle save) {
        initClick();
        initHttp();
    }

    private void initHttp(){
        Https.getCarAPI().getCarBaseInfo(user_name)
                .enqueue(new Callback<CarBaseInfo>() {
                    @Override
                    public void onResponse(Call<CarBaseInfo> call, Response<CarBaseInfo> response) {
                        if (response.body().getState() == 200) {
                            shoppingCarInfos = response.body().getShoppingCarInfos();
                            if (shoppingCarInfos.size() > 0) {
                                initData();
                            } else {
                                initEmpty();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CarBaseInfo> call, Throwable t) {
                        Toast.makeText(getContext(),"失败!",Toast.LENGTH_LONG).show();
                        initEmpty();
                    }
                });
    }

    public void initClick(){
        carDataButton.setOnClickListener(this);
        carEdit.setOnClickListener(this);

        carCheckDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mAdapter.checkAll_none(true);
                }else{
                    mAdapter.checkAll_none(false);
                }
            }
        });

        carDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    public void initData() {
        carEdit.setVisibility(View.VISIBLE);
        carAll.setVisibility(View.VISIBLE);
        carEmpty.setVisibility(View.GONE);
        mAdapter = new ShoppingCarAdapter(getContext(),shoppingCarInfos,carCheckButton,carMoney);
        carRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false));
        carRecycler.setAdapter(mAdapter);
    }

    public void initEmpty() {
        carEdit.setVisibility(View.GONE);
        carAll.setVisibility(View.GONE);
        carEmpty.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.car_edit, R.id.btn_delete,
            R.id.btn_collection, R.id.car_data_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.car_edit:
                if(edit_state){
                    showDelete();
                }else{
                    hideDelete();
                }
                break;
            case R.id.btn_delete:
                mAdapter.deleteData();
                if(mAdapter.getItemCount() <= 0){
                    initEmpty();
                }
                break;
            case R.id.btn_collection:
                Toast.makeText(getContext(),"收藏",Toast.LENGTH_LONG).show();
                break;
            case R.id.car_data_button:
                Toast.makeText(getContext(),"去购物!",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void showDelete(){
        carEdit.setText(getResources().getString(R.string.car_edit_false));
        carCheckAll.setVisibility(View.GONE);
        carDelete.setVisibility(View.VISIBLE);
        if(mAdapter !=null){
            mAdapter.checkAll_none(false);
        }
        edit_state = false;
    }

    private void hideDelete(){
        carEdit.setText(getResources().getString(R.string.car_edit_true));
        carCheckAll.setVisibility(View.VISIBLE);
        carDelete.setVisibility(View.GONE);
        if(mAdapter !=null){
            mAdapter.checkAll_none(true);
            mAdapter.showTotalPrice();
        }
        edit_state = true;
    }
}












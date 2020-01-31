package com.vargancys.vargancysshop.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/25
 * version:1.0
 */
public abstract class BaseFragment extends RxFragment {
    private View parentView;
    protected boolean isVisible;
    private Unbinder bind;
    private Context mContext;

    public abstract
    @LayoutRes
    int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    public Context getContext(){
        return mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        parentView = inflater.inflate(getLayoutId(),container,false);
        return parentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this,view);
        finishCreateView(savedInstanceState);
    }

    public abstract void finishCreateView(Bundle save);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    protected void lazyLoad(){};

    protected void initRefreshLayout(){};

    protected void loadData(){};

    protected void initRecyclerView(){};

    protected void finishTask(){};

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else{
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){lazyLoad();}

    protected void onInvisible(){};
}

package com.vargancys.vargancysshop.module.common;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.base.BaseActivity;
import com.vargancys.vargancysshop.utils.Resources;
import com.vargancys.vargancysshop.utils.Utils;

import java.util.Random;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/24
 * version:1.0
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_skip)
    TextView welcomeSkip;
    @BindView(R.id.welcome_name)
    TextView welcomeName;
    @BindView(R.id.welcome_version)
    TextView welcomeVersion;
    @BindView(R.id.welcome_background)
    RelativeLayout welcomeBackground;

    public static final int BACKGROUND_SIZE=5;

    public Random mRandom=new Random();
    public CountDownTimer mTime = new CountDownTimer(4000,1000) {
        @Override
        public void onTick(long time) {
            welcomeSkip.setText("跳过 "+(time/1000));
        }

        @Override
        public void onFinish() {
            MainActivity.launch(WelcomeActivity.this);
            finish();
        }
    };

    @Override
    public int getLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView(Bundle save) {
        initBackground();
        initAppData();
        initTime();
    }

    public void initTime(){
        welcomeSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(WelcomeActivity.this);
                mTime.cancel();
                finish();
            }
        });
        mTime.start();
    }

    public void initAppData(){
        welcomeName.setText(Resources.getString(this,R.string.app_name));
        welcomeVersion.setText(Utils.getVersion(getContext()));
    }

    public void initBackground(){
        int[] WelcomeBackground={
                R.drawable.welcome_background_1,R.drawable.welcome_background_2,
                R.drawable.welcome_background_3,R.drawable.welcome_background_4,
                R.drawable.welcome_background_5
        };
        welcomeBackground.setBackground(
                Resources.getDrawable(this,WelcomeBackground[mRandom.nextInt(BACKGROUND_SIZE)]));
        welcomeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(WelcomeActivity.this);
                WebActivity.launch(WelcomeActivity.this,"你好!");
                mTime.cancel();
                finish();
            }
        });
    }
}

package com.cozing.rxjava2retrofit2hybrid;

import android.content.Intent;
import android.view.View;

import com.cozing.rxjava2retrofit2hybrid.base.BaseActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.Rxjava2Activity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2retrofit2.RxJava2Retrofit2HybridActivity;

/**
 * desc:
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/13
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        setOnclickListenerEvent(R.id.rxjava_btn);
        setOnclickListenerEvent(R.id.rxjava_retrofit_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rxjava_btn:
                Intent intent1 = new Intent(MainActivity.this, Rxjava2Activity.class);
                startActivity(intent1);
                break;

            case R.id.rxjava_retrofit_btn:
                Intent intent2 = new Intent(MainActivity.this, RxJava2Retrofit2HybridActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void setOnclickListenerEvent(int resId){
        findViewById(resId).setOnClickListener(this);
    }
}

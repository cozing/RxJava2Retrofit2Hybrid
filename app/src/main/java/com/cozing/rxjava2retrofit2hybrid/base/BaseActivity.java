package com.cozing.rxjava2retrofit2hybrid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * desc:
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/14
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected Context mContext;

    protected abstract int getContentLayout();

    protected abstract void initViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getContentLayout() != 0){
            setContentView(getContentLayout());
            initViews();
        }
        mContext = this;
    }
}

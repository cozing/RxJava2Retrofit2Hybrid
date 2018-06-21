package com.cozing.rxjava2retrofit2hybrid.base;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cozing.rxjava2retrofit2hybrid.R;

/**
 * desc:
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/15
 */

public abstract class BaseOperatorActivity extends BaseActivity{

    protected Button operator_btn;
    protected TextView operator_tv;

    protected abstract String getOperatorTheme();
    protected abstract void clickEvent();

    protected void appendText(String text){
        operator_tv.append(text);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_base_operator;
    }

    @Override
    protected void initViews() {

        operator_btn = findViewById(R.id.operator_btn);
        operator_tv = findViewById(R.id.operator_tv);

        operator_btn.setText(getOperatorTheme() == null ? "ClickEvent" : getOperatorTheme());
        operator_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent();
            }
        });

    }
}

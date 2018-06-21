package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc:elememtAt操作符演示
 * <p>
 *
 *     指定接收某个值（通过索引值决定）。
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class ElementAtActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "elememtAt";
    }

    @Override
    protected void clickEvent() {

        Observable.just("1", "21", "31", "45", "1")
                .elementAt(3) //接收位置处于第三个的数据
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        appendText("无默认 接收到的数据：" + s + "\n");
                    }
                });

        Observable.just("1", "32", "121", "54")
                .elementAt(8, "100") //接收位置处于第8个的数据，没有值默认"100"
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                appendText("有默认值 接收到的数据：" + s + "\n");
            }
        });

    }
}

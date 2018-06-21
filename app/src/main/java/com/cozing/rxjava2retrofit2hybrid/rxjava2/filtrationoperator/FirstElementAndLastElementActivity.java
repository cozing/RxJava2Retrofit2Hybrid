package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc:firstElement/lastElement操作符演示
 * <p>
 *
 *
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class FirstElementAndLastElementActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "firstElement/lastElement";
    }

    @Override
    protected void clickEvent() {

        Observable.just("1", "2", "3", "4", "5")
                .firstElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        appendText("firstElement 接收数据：" + s + "\n");
                    }
                });

        Observable.just("a", "b", "c", "d")
                .lastElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        appendText("lastElement 接收数据" + s + "\n");
                    }
                });
    }
}

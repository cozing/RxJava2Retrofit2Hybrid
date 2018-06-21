package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc:count操作符演示
 * <p>
 *     统计被观察者发送事件的数量
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class CountActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "count";
    }

    @Override
    protected void clickEvent() {

        Observable.just("1", "2", "3", "4", "5")
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        appendText("发送的事件数量为：" + aLong);
                    }
                });

    }
}

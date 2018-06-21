package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * desc:reduce操作符演示
 * <p>
 *     把被观察者发送的事件聚合成一个事件并发送
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class ReduceActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "reduce";
    }

    @Override
    protected void clickEvent() {

        Observable.just(1, 2, 3, 4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        appendText("此次计算的数据是" +integer + " * " + integer2 + "\n");

                        //本次聚合的逻辑是：全部数据相乘
                        //原理：首次返回前两个数据相乘，以后每次返回前面计算的结果乘以后一个数据的结果
                        return integer * integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        appendText( "结论：" + integer + "\n");
                    }
                });

    }
}

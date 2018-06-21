package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * desc:combineLatest操作符演示
 * <p>
 *     组合两个被观察者，将先发送数据的被观察者的最新的（最后的）一个数据与另一个被观察者发送的每一个数据结合，最终结合成新的数据发送。
 *     与zip不同点：zip是按照个数发送，1对1合并，而combinelatest是按照时间合并，同一个时间点的上合并。
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class CombineLatestActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "combineLatest";
    }

    @Override
    protected void clickEvent() {

        Observable.combineLatest(
                Observable.just("A", "B", "C"),
                Observable.intervalRange(1, 4, 2, 1, TimeUnit.SECONDS),
                new BiFunction<String, Long, String>() {
                    @Override
                    public String apply(String s, Long aLong) throws Exception {
                        return s + "-----" + aLong;
                    }
                }
        ).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                appendText(s + "\n");
            }
        });
    }
}

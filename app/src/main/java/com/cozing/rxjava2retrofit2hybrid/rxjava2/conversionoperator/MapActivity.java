package com.cozing.rxjava2retrofit2hybrid.rxjava2.conversionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * desc:Map操作符演示
 * <p>
 *     1.将被观察者发送的事件转换成其他事件并发送
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class MapActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "map";
    }

    @Override
    protected void clickEvent() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("我是原始事件1");
                e.onNext("我是原始事件2");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return "我是被改过后的数据 + " + s + "\n";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                appendText(s);
            }
        });
    }
}

package com.cozing.rxjava2retrofit2hybrid.rxjava2.conversionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * desc:flatMap操作符演示
 * <p> 将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class FlatMapActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "flatMap";
    }

    @Override
    protected void clickEvent() {

        //采用事件流的链式操作
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("数据1");
                e.onNext("数据2");
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {

                //对事件进行拆分
                List<String> list = new ArrayList<>();
                for(int i = 0; i < 3; i ++){
                    list.add("拆分" + i);
                }

                //对拆分成对的新事件进行组合成新的observable并重新发送
                return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                appendText(s + "\n");
            }
        });
    }
}

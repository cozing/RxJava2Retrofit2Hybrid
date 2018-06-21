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
 * desc:concatMap 操作符演示
 * <p>
 *     功能类似flatmap，不同点：拆分后重新合并生成的事件序列的顺序 == 原始被观察者旧事件序列的生产顺序
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class ConcatMapActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "concatMap";
    }

    @Override
    protected void clickEvent() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("数据1");
                e.onNext("数据2");
            }
        }).concatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                List<String> list = new ArrayList<>();
                for(int i = 0; i < 3; i++){
                    list.add("拆分" + i);
                }
                return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        appendText(s + "\n");
                    }
                });


    }
}

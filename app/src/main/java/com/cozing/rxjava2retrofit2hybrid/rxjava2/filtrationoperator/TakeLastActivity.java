package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:takeLast操作符演示
 * <p>
 *
 *     指定观察者只能接收的被观察者的最后几个事件
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class TakeLastActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "takeLast";
    }

    @Override
    protected void clickEvent() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onNext("4");
                e.onComplete();
            }
        }).takeLast(2)//指定观察者接收被观察者最后2个数据
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("onComplete" + "\n");
                    }
                });
    }
}

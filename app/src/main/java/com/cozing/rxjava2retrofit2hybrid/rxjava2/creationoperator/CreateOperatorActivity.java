package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc: create操作符演示
 * 正常情况下创建一个被观察者对象
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/15
 */

public class CreateOperatorActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "create";
    }

    @Override
    protected void clickEvent() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                appendText("onSubscribe" + "\n");
            }

            @Override
            public void onNext(String s) {
                appendText("onNext:" + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                appendText("onError" + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                appendText("onComplete" + "\n");
            }
        });
    }


}

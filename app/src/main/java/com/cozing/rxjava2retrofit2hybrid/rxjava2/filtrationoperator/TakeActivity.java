package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:take操作符演示
 * <p>
 *
 *     指定观察者最多能接受的事件数量
 *     说明：实际被观察者的数量并没有改变，是take操作符的存在拦截了事件数量
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class TakeActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "take";
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
        }).take(3)//指定观察者最多接收3个数据
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

package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:subscribe操作符演示
 * <p>
 *     订阅 连接被观察者和观察者
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class SubscribeActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "subscribe";
    }

    @Override
    protected void clickEvent() {

        //1.声明一个被观察者
        Observable observable = Observable.just("这是被观察者需要传递的数据");

        //2.声明一个观察者
        Observer observer = new Observer<String>() {
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
                appendText(e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                appendText("onComplete" + "\n");
            }
        };

        //3.订阅
        observable.subscribe(observer);

    }
}

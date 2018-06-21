package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:defer操作符演示
 * <p> 直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） 并发送事件
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class DeferActivity extends BaseOperatorActivity{

    private String tagger = "这是第一次赋值";

    @Override
    protected String getOperatorTheme() {
        return "defer";
    }

    @Override
    protected void clickEvent() {


        appendText(tagger + "\n");

        Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource call() throws Exception {
                return Observable.just(tagger);
            }
        });

        tagger = "这是第二次赋值";

        observable.subscribe(new Observer<String>() {
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

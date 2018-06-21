package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:sample操作符演示
 *
 *      在某段时间内只发送最新（最后一个数据），与throttleLast操作符使用方式一样
 *
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class SampleActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "sample";
    }

    @Override
    protected void clickEvent() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                Thread.sleep(200);

                e.onNext("2");
                Thread.sleep(200);

                e.onNext("3");
                Thread.sleep(200);

                e.onNext("4");
                Thread.sleep(200);

                e.onNext("5");
                Thread.sleep(200);

                e.onNext("6");
                Thread.sleep(200);

                e.onNext("7");
                Thread.sleep(200);

                e.onNext("8");
                Thread.sleep(200);

                e.onNext("9");
                Thread.sleep(200);

                e.onNext("10");
                Thread.sleep(200);

                e.onComplete();
            }
        }).sample(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText(" onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText(" onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText(" onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText(" onComplete" + "\n");
                    }
                });
    }
}

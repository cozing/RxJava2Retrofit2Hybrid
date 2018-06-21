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
 * desc:throttleFirst/throttleLast操作符演示
 * <p>
 *
 *     在某段时间内，只发送该段事件的第一/最后一次事件
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class ThrottleFirstAndThrottleLastActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "throttleFirst/throttleLast";
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
        }).throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("throttleFirst onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("throttleFirst onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("throttleFirst onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("throttleFirst onComplete" + "\n");
                        throttleLast();
                    }
                });

    }

    private void throttleLast(){
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
        }).throttleLast(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("throttleLast onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("throttleLast onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("throttleLast onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("throttleLast onComplete" + "\n");
                    }
                });
    }
}

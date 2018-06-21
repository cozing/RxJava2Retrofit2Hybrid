package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:
 *      1.快速创建一个被观察者对象obervable
 *      2.每隔一个固定时间就发送一次事件
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class IntervalActivity extends BaseOperatorActivity{

    @Override
    protected String getOperatorTheme() {
        return "interval";
    }

    @Override
    protected void clickEvent() {

        /**
         * 参数解析：
         *  参数1：首次延迟时间
         *  参数2：间隔时间
         *  参数3：时间单位
         *  参数4：调度线程
         */
        Observable.interval(2, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        appendText("onNext:" + aLong + "\n");
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

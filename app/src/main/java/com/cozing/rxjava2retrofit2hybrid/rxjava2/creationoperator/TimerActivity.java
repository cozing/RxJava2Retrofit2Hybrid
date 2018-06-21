package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:timer操作符演示
 * <p>  1.快速创建1个被观察者对象（Observable）
 *       2.发送事件的特点：延迟指定时间后，发送1个数值0（Long类型）
 *
 *       注意：timer操作符默认运行一个新的线程，可以通过Observable.timer(Long delay, TimeUnit unit, Scheduler scheduler)定义调度线程
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class TimerActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "timer";
    }

    @Override
    protected void clickEvent() {

        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
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

package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:intervalRange操作符演示
 *
 *  1.快速创建一个被观察者对象observable
 *  2.每隔指定时间发送事件，可指定发送的事件个数
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class IntervalRangeActivity extends BaseOperatorActivity {
    @Override
    protected String getOperatorTheme() {
        return "intervalRange";
    }

    @Override
    protected void clickEvent() {

        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        Observable.intervalRange(5, 10, 2, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
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

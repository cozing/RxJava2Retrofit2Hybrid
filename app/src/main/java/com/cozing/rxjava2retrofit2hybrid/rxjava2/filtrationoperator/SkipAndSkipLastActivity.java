package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:skip/skiplast操作符演示
 * <p>
 *
 *     跳过某个事件
 *
 *     比如：skip(2)跳过正序的前二项
 *          skipLast(3)跳过正序的后二项
 *
 *          skip(1, TimeUnit.SECONDS)跳过第一秒发送的数据
 *          skipLast(2, TimeUnit.SECONDS)跳过后二秒发送的数据
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class SkipAndSkipLastActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "skip/skiplast";
    }

    @Override
    protected void clickEvent() {
        Observable.just("1", "2", "3", "4", "5")
                .skip(2) //跳过正序前2项
                .skipLast(1) //跳过正序后一项
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("skip onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("skip onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("skip onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("skip onComplete" + "\n");
                        skipOnTimeTest();
                    }
                });
    }

    //根据事件跳过数据项
    private void skipOnTimeTest(){

        Observable.intervalRange(1, 8, 1, 1, TimeUnit.SECONDS)
                .skip(2, TimeUnit.SECONDS)
                .skipLast(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("skipOnTimeTest onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        appendText("skipOnTimeTest onNext：" + aLong + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("skipOnTimeTest onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("skipOnTimeTest onComplete" + "\n");
                    }
                });

    }
}

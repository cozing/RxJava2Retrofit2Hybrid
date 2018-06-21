package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:merge /mergeArray操作符演示
 * <p>
 *     merge: 组合多个被观察者一起发送数据，合并后按照时间线并行发送（被组合的观察者数量 <= 4）
 *     mergeArray:组合多个被观察者一起发送数据，合并后按照时间线并行发送（被组合的观察者数量 > 4）
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class MergeAndMergeArrayActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "merge/mergeArray";
    }

    @Override
    protected void clickEvent() {

        Observable.merge(
                Observable.intervalRange(1, 2, 5, 1, TimeUnit.SECONDS),//从1开始，共2个数据，第一个数据延迟5s，以后每个数据发送间隔1s
                Observable.intervalRange(1, 2, 5, 1, TimeUnit.SECONDS),
                Observable.intervalRange(1, 2, 5, 1, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("merge onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Long s) {
                        appendText("merge onNext:" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("merge onError" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("merge onComplete" + "\n");
                        mergeArrayTest();
                    }
                });
    }

    private void mergeArrayTest(){
        Observable.mergeArray(Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS),
                Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS),
                Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS),
                Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS),
                Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("merge onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        appendText("merge onNext:" + aLong + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("merge onError" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("merge onComplete" + "\n");
                    }
                });
    }
}

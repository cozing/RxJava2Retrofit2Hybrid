package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * desc:zip操作符演示
 * <p>
 *     合并多个被观察者发送的事件，组合生成一个新的事件序列，最终发送。
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class ZipActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "zip";
    }

    @Override
    protected void clickEvent() {

        //被观察者对象1
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("被观察者1：1");
                Thread.sleep(500);

                e.onNext("被观察者1：2");
                Thread.sleep(500);

                e.onNext("被观察者1：3");
                Thread.sleep(500);

                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());//设置被观察者1在工作线程中工作

        //被观察者对象2
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("被观察者2：A");
                Thread.sleep(500);

                e.onNext("被观察者2：B");
                Thread.sleep(500);

                e.onNext("被观察者2：C");
                Thread.sleep(500);

                e.onNext("被观察者2：D");
                Thread.sleep(500);

                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());//设置被观察者2在工作线程2中工作，若不对线程做控制，
        // 则两个被观察者工作于同一个线程，发送的事件有先后顺序，不是同时发送


        Observable.zip(observable1, observable2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s + s2;
            }
        }).subscribe(new Observer<String>() {
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

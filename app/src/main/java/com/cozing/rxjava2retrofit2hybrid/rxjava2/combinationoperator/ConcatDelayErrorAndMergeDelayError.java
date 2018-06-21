package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:concatDelayError/mergeDelayError操作符演示
 * <p>
 *     作用：在使用concat和merge操作符时，若前面的被观察者发出onError事件，则会马上终止其他被观察者继续发送事件
 *     使用concatDelayError/mergeDelayError操作符可以使在前面的被观察者发送了onError事件的情况下还能让其他被观察者继续发送事件
 *     直到其他被观察者的事件发送结束后才触发onError.
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class ConcatDelayErrorAndMergeDelayError extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "concatDelayError/MergeDelayError";
    }

    @Override
    protected void clickEvent() {
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {

                        e.onNext("1");
                        e.onNext("2");
                        e.onNext("3");
                        e.onError(new Throwable("onError测试"));
                        e.onComplete();
                    }
                }),
                Observable.just("4", "5", "6", "7")
        ).subscribe(new Observer<String>() {
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

package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:onExceptionResumeNext操作符演示
 * <p>
 *
 *     当发生错误时，发送一个新的被观察者 Observable
 *
 *     注意：
 *          若拦截的错误是Thrawable，最后数据传递给观察者的onError();
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class OnExceptionResumeNextActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "onExceptionResumeNext";
    }

    @Override
    protected void clickEvent() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
//                e.onError(new Throwable("这是错误Throwable"));
                e.onError(new Exception("这是错误Exception"));
                e.onNext("4");
            }
        }).onExceptionResumeNext(new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                //拦截到错误："这是错误！"后，重新发送一个带有参数"发生错误后拦截重新产生的数据是我~"的被观察者，并正常结束
                observer.onNext("发生错误后拦截重新产生的数据是我~");
                observer.onComplete();
            }
        }
        ).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                appendText("onSubscribe\n");
            }

            @Override
            public void onNext(String s) {
                appendText("onNext:" + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                appendText("oError:" + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                appendText("onComplete" + "\n");
            }
        });
    }
}

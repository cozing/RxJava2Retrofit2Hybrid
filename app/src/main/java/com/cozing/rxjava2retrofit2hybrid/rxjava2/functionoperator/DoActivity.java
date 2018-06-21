package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * desc:do操作符演示
 * <p>
 *     do操作符有很多：
 *     1.doOnEach：          当Observable每发送1次数据事件就会调用1次
 *     2.doOnNext：          执行Next事件前调用
 *     3.doAfterNext：       执行Next事件后调用
 *     4.doOnComplete：      Observable正常发送事件完毕后调用
 *     5.doOnError：         Observable发送错误事件时调用
 *     6.doOnSubscribe：     观察者订阅时调用
 *     7.doAfterTerminate：  Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
 *     8.doFinally：         最后执行
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class DoActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "do";
    }

    @Override
    protected void clickEvent() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        appendText("doOnEach: " + integerNotification.getValue() + "\n");
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        appendText("doOnNext: " + integer + "\n");
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        appendText("doAfterNext: " + integer + "\n");
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        appendText("doOnComplete" + "\n");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        appendText("doOnError: " + throwable.getMessage() + "\n");
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        appendText("doOnSubscribe: " + "\n");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        appendText("doAfterTerminate: " + "\n");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        appendText("doFinally: " + "\n");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }
                    @Override
                    public void onNext(Integer value) {
                        appendText("接收到了事件"+ value   + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("对Error事件作出响应" + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("对Complete事件作出响应" + "\n");
                    }
                });

    }
}

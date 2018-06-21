package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * desc:repeatWhen操作符演示
 * <p>
 *     有条件，重复发送被观察者事件
 *
 *     原理：
 *     将原始 Observable 停止发送事件的标识（Complete（） / Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable），以此决定是否重新订阅 & 发送原来的 Observable
 *
 *      1.若新被观察者（Observable）返回1个Complete / Error事件，则不重新订阅 & 发送原来的 Observable
 *      2.若新被观察者（Observable）返回其余事件时，则重新订阅 & 发送原来的 Observable
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class RepeatWhenActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "repeatWhen";
    }

    @Override
    protected void clickEvent() {
        Observable.just(1,2,3).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
//                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                         return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        // return Observable.just(1);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                    }
                });

            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Integer value) {
                        appendText("onNext：" + value + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("onComplete" + "\n");
                    }

                });
    }
}

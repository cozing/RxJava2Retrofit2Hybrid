package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * desc:delay操作符演示
 * <p>
 *     让被观察者延迟一段时间后再发送事件
 *
 * 1. 指定延迟时间
 * 参数1 = 时间；参数2 = 时间单位
 * delay(long delay,TimeUnit unit)
 *
 * 2. 指定延迟时间 & 调度器
 * 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器
 * delay(long delay,TimeUnit unit,mScheduler scheduler)
 *
 * 3. 指定延迟时间  & 错误延迟
 * 错误延迟，即：若存在Error事件，则如常执行，执行后再抛出错误异常
 * 参数1 = 时间；参数2 = 时间单位；参数3 = 错误延迟参数
 * delay(long delay,TimeUnit unit,boolean delayError)
 *
 * 4. 指定延迟时间 & 调度器 & 错误延迟
 * 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器；参数4 = 错误延迟参数
 * delay(long delay,TimeUnit unit,mScheduler scheduler,boolean delayError): 指定延迟多长时间并添加调度器，错误通知可以设置是否延迟
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class DelayActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "delay";
    }

    @Override
    protected void clickEvent() {

        Observable.just("1", "2", "3")
                .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText(e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("onComplete" + "\n");
                    }
                });
    }
}

package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * desc:retry操作符演示
 * <p>
 *
 *     重试，当发射过程中出现错误，让被观察者重新发射
 *
 *     1.接收到 onError（）时，重新订阅 & 发送事件
 *     2.Throwable 和 Exception都可拦截
 *
 *     使用方法：
 *     1.
 *     <-- 1. retry（） -->
 *      作用：出现错误时，让被观察者重新发送数据
 *      注：若一直错误，则一直重新发送
 *
 *     <-- 2. retry（long time） -->
 *      作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
 *      参数 = 重试次数
 *
 *     <-- 3. retry（Predicate predicate） -->
 *      作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
 *      参数 = 判断逻辑
 *
 *     <--  4. retry（new BiPredicate<Integer, Throwable>） -->
 *      作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
 *      参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
 *
 *     <-- 5. retry（long time,Predicate predicate） -->
 *      作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
 *      参数 = 设置重试次数 & 判断逻辑
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class RetryActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "retry";
    }

    @Override
    protected void clickEvent() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onError(new Throwable("发生错误！"));
                e.onNext("4");
            }
        }).retry(3, new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {

                return true;
            }
        }).subscribe(new Observer<String>() {
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

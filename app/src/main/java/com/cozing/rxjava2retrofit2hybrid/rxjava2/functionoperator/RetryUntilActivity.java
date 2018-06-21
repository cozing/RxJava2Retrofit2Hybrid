package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BooleanSupplier;

/**
 * desc:retryUntil操作符演示
 * <p>
 *     出现错误后重试，和retry操作符类似，具体使用情况请参考retry操作符
 *     不同点：
 *     返回 true不在继续重新发送数据
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class RetryUntilActivity extends BaseOperatorActivity{

    int tag = 1;

    @Override
    protected String getOperatorTheme() {
        return "retryUntil";
    }

    @Override
    protected void clickEvent() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onNext("4");
                e.onError(new Throwable("出错啦~"));
                e.onNext("5");
            }
        }).retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Exception {
                if(tag == 3){
                    return true;
                }
                tag ++;
                return false;
            }
        }).subscribe(new Observer<String>() {
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

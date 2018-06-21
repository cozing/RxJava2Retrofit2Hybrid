package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:repeat
 * <p>
 *
 *     repeat():无条件的，重复发送被观察事件
 *     repeat(Long time): 重复time此发送被观察者事件
 *
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class RepeatActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "repeat";
    }

    @Override
    protected void clickEvent() {

        Observable.just("1", "2", "3")
                .repeat(3)
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
                        appendText("onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("onComplete" + "\n");
                    }
                });

    }
}

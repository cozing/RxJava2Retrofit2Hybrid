package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:range操作符演示
 * <p>
 *     1.快速创建一个被观察者对象 observable
 *     2.连续发送一个事件，可指定范围
 *     3.与intervalRange对比：没有延迟，直接发送事件
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class RangeActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "range";
    }

    @Override
    protected void clickEvent() {

        //参数1：事件起始点
        //参数2：总共发送的事件
        Observable.range(5, 10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        appendText("onNext:" + integer + "\n");
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

package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:rangeLong操作符演示
 * <p>
 *     1.快速创建一个被观察者对象Obsevable
 *     2.连续发送一个事件
 *     3.和range差异：支持的数据类型是long
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class RangeLongActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "rangeLong";
    }

    @Override
    protected void clickEvent() {

        Observable.rangeLong(6, 4)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        appendText("onNext:" + aLong + "\n");
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

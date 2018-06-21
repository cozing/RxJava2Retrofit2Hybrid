package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:fromArray操作符演示
 * 1.快速创建1个被观察者对象（Observable）,2.发送事件的特点：直接发送 传入的数组数据
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/15
 */

public class FromArrayOperatorActivity extends BaseOperatorActivity{

    private String[] arr = {"1", "2", "3"};

    @Override
    protected String getOperatorTheme() {
        return "fromArray";
    }

    @Override
    protected void clickEvent() {


        Observable.fromArray(arr).subscribe(new Observer<String>() {
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

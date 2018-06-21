package com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:fromIterable操作符演示
 * <p>1.快速创建1个被观察者对象（Observable）, 2.发送事件的特点：直接发送 传入的集合List数据
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/15
 */

public class FromIterableOperatorActivity extends BaseOperatorActivity{

    private List<String> list = new ArrayList<>();

    @Override
    protected String getOperatorTheme() {
        return "fromIterable";
    }

    @Override
    protected void clickEvent() {
        list.add("1");
        list.add("2");
        list.add("3");

        Observable.fromIterable(list).subscribe(new Observer<String>() {
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

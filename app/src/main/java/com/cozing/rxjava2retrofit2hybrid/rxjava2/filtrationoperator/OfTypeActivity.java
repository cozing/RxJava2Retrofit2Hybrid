package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:ofType操作符演示
 * <p>
 *
 *     过滤特定数据类型的数据
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class OfTypeActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "ofType";
    }

    @Override
    protected void clickEvent() {

        Observable.just(1, "A", 2, "b", "c", 3)
                //筛选整形的数据类型
                .ofType(Integer.class)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        appendText("onNext：" + integer + "\n");
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

package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:distinct / distinctUntilChanged操作符演示
 * <p>
 *
 *     过滤事件序列中重复的事件/连续重复的事件
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class DistinctAndDistinctUtilChangeActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "distinct / distinctUntilChanged";
    }

    @Override
    protected void clickEvent() {

        //过滤事件序列中重复的事件
        Observable.just("1", "1", "2", "3", "3", "4")
                .distinct()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("distinct onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("distinct onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("distinct onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("distinct onComplete" + "\n");
                        distinctUntilChanged();
                    }
                });
    }

    //过滤事件序列中连续重复的事件
    private void distinctUntilChanged(){
        Observable.just("1", "1", "2", "3", "3", "4", "4", "5")
                .distinctUntilChanged()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("distinctUntilChanged onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("distinctUntilChanged onNext：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("distinctUntilChanged onError:" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("distinctUntilChanged onComplete" + "\n");
                    }
                });
    }
}

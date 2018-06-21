package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:concat / concatArray操作符演示
 * <p>
 *     concat:组合多个被观察者一起发送数据，合并后按发送顺序串行执行(被观察者数量 =< 4)
 *     concatArray:组合多个被观察者一起发送数据，合并后按发送顺序串行执行（被观察者数量 > 4）
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class ConcatAndConcatArrayActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "concat/concatArray";
    }

    @Override
    protected void clickEvent() {
        Observable.concat(Observable.just("1"), Observable.just("2"), Observable.just("3"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("concat onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("concat onNext:" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("concat onError" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("concat onComplete" + "\n");
                        concatArrayTest();
                    }
                });


    }

    private void concatArrayTest(){
        Observable.concatArray(Observable.just("4"),Observable.just("5"),Observable.just("6"),Observable.just("7"),Observable.just("8"),Observable.just("9"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("concatArray onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("concatArray onNext:" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("concatArray onError" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("concatArray onComplete" + "\n");
                    }
                });
    }
}

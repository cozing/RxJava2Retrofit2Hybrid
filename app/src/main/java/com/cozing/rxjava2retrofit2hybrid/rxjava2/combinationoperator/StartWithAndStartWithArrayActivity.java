package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc:startWith/startWithArray操作符演示
 * <p>
 *     在被观察者发送事件之前，追加发送一些数据或者一个新的被观察者
 *     startWith：追加单个数据
 *     startWithArray:追加多个数据
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class StartWithAndStartWithArrayActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "startWith/startWithArray";
    }

    @Override
    protected void clickEvent() {

        //在被观察者发送数据之前新增数据
        Observable.just("1", "2", "3", "4")
                .startWith("A")
                .startWithArray("6a", "7a", "8a")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("startWith onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("startWith 结果：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText("startWith" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("startWith onComplete" + "\n");
                        startWithObservableTest();
                    }

                });



    }

    private void startWithObservableTest(){
        //在被观察者发送数据之前新增一个被观察者
        Observable.just("1", "2", "3", "4")
                .startWith(Observable.just("A", "B", "C"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        appendText("startWithObservableTest onSubscribe" + "\n");
                    }

                    @Override
                    public void onNext(String s) {
                        appendText("startWithObservableTest 结果：" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        appendText(" startWithObservableTest" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        appendText("startWithObservableTest onComplete" + "\n");
                    }
                });
    }
}

package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * desc:filter操作符演示
 * <p>
 *
 *      过滤特定条件的事件
 *
 *      Observable.filter(new Predicate<Integer>() {
 *      // 根据test()的返回值 对被观察者发送的事件进行过滤 & 筛选
 *      // a. 返回true，则继续发送
 *      // b. 返回false，则不发送（即过滤）
 *      @Override
 *      public boolean test(Integer integer) throws Exception {
 *      return integer > 3;
 *      过滤了整数≤3的事件
 *      }
 *   })
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class FilterActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "filter";
    }

    @Override
    protected void clickEvent() {

        Observable.just(1, 2, 3, 4, 5).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 3;
            }
        }).subscribe(new Observer<Integer>() {
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

package com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * desc:onErrorReturn操作符演示
 * <p>
 *
 *     发生错误时发送一个特殊事件并终止订阅
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class OnErrorReturnActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "onErrorReturn";
    }

    @Override
    protected void clickEvent() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("a");
                e.onNext("b");
                e.onError(new Throwable("发生错误！"));
                e.onNext("e");
                e.onComplete();
            }
        }).onErrorReturn(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) throws Exception {
                //发生错误后，发送该事件并正常结束
                return "发生错误后发送的数据就是我~";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                appendText("onSubscribe\n");
            }

            @Override
            public void onNext(String s) {
                appendText("onNext:" + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                appendText("oError:" + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                appendText("onComplete" + "\n");
            }
        });
    }
}

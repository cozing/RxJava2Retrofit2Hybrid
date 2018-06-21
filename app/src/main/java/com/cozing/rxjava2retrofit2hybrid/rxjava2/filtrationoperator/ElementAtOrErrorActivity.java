package com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc:elementAtOrError
 * <p>
 *
 *     在elementAt基础上进化，当索引的位置越界时，抛出异常
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/21
 */

public class ElementAtOrErrorActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        appendText("注意！该操作符演示会造成APP挂掉，需在log日志上查看演示结果\n");
        return "elementAtOrError";
    }

    @Override
    protected void clickEvent() {

        Observable.just("1", "32", "34", "435", "546")
                .elementAtOrError(10)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        appendText("接收到的数据：" + s +"\n");
                    }
                });
    }
}

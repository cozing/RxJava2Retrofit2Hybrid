package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * desc:collect
 * <p>
 *     将被观察者发送的数据收集到一个数据结构里
 *
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class CollectActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "collect";
    }

    @Override
    protected void clickEvent() {

        Observable.just("1", "2", "3", "4", "5")
                .collect(
                        //1.创建数据结构（容器），用于收集被观察者发送的数据
                        new Callable<ArrayList<String>>() {
                    @Override
                    public ArrayList<String> call() throws Exception {
                        return new ArrayList<>();
                    }
                },
                        //2.收集发送的数据
                        new BiConsumer<ArrayList<String>, String>() {
                    @Override
                    public void accept(ArrayList<String> list, String s) throws Exception {
                        list.add(s);
                    }
                })
        .subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> strings) throws Exception {

                appendText("接收的数据结构：" + strings);
            }
        });

    }
}

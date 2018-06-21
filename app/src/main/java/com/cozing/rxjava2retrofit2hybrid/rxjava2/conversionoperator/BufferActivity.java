package com.cozing.rxjava2retrofit2hybrid.rxjava2.conversionoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc:buffer操作符演示
 * <p>
 *     用于将被观察者发送的事件进行缓存，定期从被观察者（Obervable）需要发送的事件中获取一定数量的事件并放到缓存区中，最终发送
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/19
 */

public class BufferActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "buffer";
    }

    @Override
    protected void clickEvent() {
        Observable.just("1", "2", "3", "4", "5")
                //设置缓存区间的大小、步长
                //大小：每次从被观察者中获取的事件数量
                //步长：每次获取新事件的数量
                .buffer(3, 2)
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {

                        appendText("缓存区里的事件个数：" + strings.size() + "\n");
                        for(int i = 0; i < strings.size(); i++){
                            appendText("事件：" + strings.get(i) + "\n");
                        }

                    }
                });
    }
}

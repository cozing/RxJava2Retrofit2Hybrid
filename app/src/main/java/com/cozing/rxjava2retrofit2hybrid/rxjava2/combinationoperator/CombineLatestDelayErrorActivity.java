package com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator;

import com.cozing.rxjava2retrofit2hybrid.base.BaseOperatorActivity;

/**
 * desc:combineLatestDelayError操作符演示
 * <p>
 *     与concatDelayError使用一样，但是例子出错，后期处理
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/20
 */

public class CombineLatestDelayErrorActivity extends BaseOperatorActivity{
    @Override
    protected String getOperatorTheme() {
        return "combineLatestDelayError";
    }

    @Override
    protected void clickEvent() {
    }
}

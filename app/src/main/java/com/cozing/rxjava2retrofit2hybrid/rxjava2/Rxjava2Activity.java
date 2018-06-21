package com.cozing.rxjava2retrofit2hybrid.rxjava2;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cozing.rxjava2retrofit2hybrid.R;
import com.cozing.rxjava2retrofit2hybrid.adapter.MRVAdapter;
import com.cozing.rxjava2retrofit2hybrid.base.BaseActivity;
import com.cozing.rxjava2retrofit2hybrid.javabean.Operator;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.CollectActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.CombineLatestActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.CombineLatestDelayErrorActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.ConcatAndConcatArrayActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.ConcatDelayErrorAndMergeDelayError;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.CountActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.MergeAndMergeArrayActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.ReduceActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.StartWithAndStartWithArrayActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.combinationoperator.ZipActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.conversionoperator.BufferActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.conversionoperator.ConcatMapActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.conversionoperator.FlatMapActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.conversionoperator.MapActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.CreateOperatorActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.DeferActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.FromArrayOperatorActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.FromIterableOperatorActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.IntervalActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.IntervalRangeActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.JustOperatorActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.RangeActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.RangeLongActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.creationoperator.TimerActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.DistinctAndDistinctUtilChangeActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.ElementAtActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.ElementAtOrErrorActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.FilterActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.FirstElementAndLastElementActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.OfTypeActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.SampleActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.SkipAndSkipLastActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.TakeActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.TakeLastActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.ThrottleFirstAndThrottleLastActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.filtrationoperator.ThrottleWithTimeoutAndDebounceActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.DelayActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.DoActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.ObserveOnActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.OnErrorResumeNextActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.OnErrorReturnActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.OnExceptionResumeNextActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.RepeatActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.RepeatWhenActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.RetryActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.RetryUntilActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.RetryWhenActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.SubscribeActivity;
import com.cozing.rxjava2retrofit2hybrid.rxjava2.functionoperator.SubscribeOnActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * desc:rxjava2操作符列表
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/14
 */

public class Rxjava2Activity extends BaseActivity{

    private List<Operator> operators = new ArrayList<>();

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rxjava2;
    }

    @Override
    protected void initViews() {
        RecyclerView recycler = findViewById(R.id.recycler);

        addItems();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        MRVAdapter adapter = new MRVAdapter(operators, new MRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                itemClickEvent(position);
            }
        });
        recycler.setAdapter(adapter);
    }

    private void addItems(){
        //用一个Item说明下面是创建操作符
        operators.add(new Operator("", "**************创建操作符**************↓"));
        //以下是创建操作符
        operators.add(new Operator("create", "正常情况下创建一个被观察者对象"));
        operators.add(new Operator("just", "快速创建一个被观察者对象，传入的参数就是发送的数据，注意：只能发送10个参数!"));
        operators.add(new Operator("fromArray", "1.快速创建1个被观察者对象（Observable）,2.发送事件的特点：直接发送 传入的数组数据 "));
        operators.add(new Operator("fromIterable", "1.快速创建1个被观察者对象（Observable）, 2.发送事件的特点：直接发送 传入的集合List数据 "));
        operators.add(new Operator("defer", "当有观察者订阅时，动态创建被观察者对象并发送事件"));
        operators.add(new Operator("timer", "timer(long,TimeUnit,Scheduler)/timer(long,TimeUnit)，快速创建一个被观察者并在被订阅时候延时long时间发送"));
        operators.add(new Operator("interval", "interval(long initialDelay, long period, TimeUnit unit)，快速创建一个被观察者并在被订阅时，首次延时initialDelay，以后每隔period时间发送一次事件"));
        operators.add(new Operator("intervalRange", "intervalRange(long start, long count, long initialDelay, long period, TimeUnit unit)和interval一样功能，但可以设定count数量的事件，并从start为事件发送起始点"));
        operators.add(new Operator("range", "range(final int start, final int count)快速创建一个被观察者对象并从start开始连续发送count个事件"));
        operators.add(new Operator("rangeLong", "rangeLong(long start, long count)和range一样，区别在于参数支持类型为long"));
        //用一个Item说明下面是变换操作符
        operators.add(new Operator("", "**************变换操作符**************↓"));
        //以下是变换操作符
        operators.add(new Operator("Map", "对被观察者发送的每1个事件都通过指定的函数处理，从而变换成另外一种事件"));
        operators.add(new Operator("FlatMap", "将被观察者发送的事件序列进行拆分并单独转换，再合并成一个新的事件序列，最后再进行发送"));
        operators.add(new Operator("ConcatMap", "与FlatMap类似，不同点：拆分并重新合并生成的事件序列的顺序 = 被观察者旧序列生产的顺序"));
        operators.add(new Operator("Buffer", "用于将被观察者发送的事件进行缓存，定期从被观察者（Obervable）需要发送的事件中获取一定数量的事件并放到缓存区中，最终发送"));
        //用一个Item说明下面是组合操作符
        operators.add(new Operator("", "********组合操作符和合并操作符********↓"));
        //以下是组合操作符
        operators.add(new Operator("concat/concatArray", "组合多个被观察者一起发送数据，合并后按发送顺序串行执行，不同点：如果组合的被观察者数量<=4使用concat， 如果>4使用concatArray"));
        operators.add(new Operator("merge/mergeArray", "组合多个被观察者一起发送数据，合并后按时间线并行执行，不同点：如果组合的被观察者数量<=4使用merge， 如果>4使用mergeArray"));
        operators.add(new Operator("concatDelayError/mergeDelayError", "当使用concatArray或mergeDelayError操作符时，若其中一个被观察者发出了onError事件，则会马上终止其他被观察者发送事件。使用该操作符可以使onError事件推迟到其他被观察者发送事件结束后才触发。"));
        //以下是合并操作符
        operators.add(new Operator("Zip", "合并多个被观察者发送的事件，并组合生成一个新的事件序列，最终发送事件"));
        operators.add(new Operator("combineLatest", "当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables的最新最后一个数据与另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据,与zip不同：zip是按照个数合并，而CombineLatest是按照时间现合并，即同一时间点上合并"));
        operators.add(new Operator("combineLatestDelayError", "和concatDelayError同样作用"));
        operators.add(new Operator("reduce", "把被观察者需要发送的事件聚合成一个事件并发送，聚合的原理：前两个数据聚合成一个，再和第三个数据聚合，以此类推"));
        operators.add(new Operator("collect", "把被观察者发送的数据收集到一个数据结构里"));
        operators.add(new Operator("startWith/startWithArray", "在被观察者发送事件前，追加一些数据，也可以追加一个新的被观察者"));
        operators.add(new Operator("count", "统计被观察者发送事件的数量"));
        //用一个Item说明下面是功能性操作符
        operators.add(new Operator("", "**************功能性操作符**************↓"));
        //以下是功能性操作符
        operators.add(new Operator("subscribe", "连接观察者和被观察者，让被观察者订阅观察者"));
        operators.add(new Operator("subscribeOn", "线程调度，声明被观察者在哪个线程（io/main）订阅，只能指定一次线程"));
        operators.add(new Operator("ObserverOn", "线程调度，声明观察者在哪个线程（io/main）接收事件并处理，可以指定多次观察者多次接收，每指定一次，都会进行一次线程的切换"));
        operators.add(new Operator("delay", "延迟操作，使被观察者延迟一段时间再发送事件"));
        operators.add(new Operator("do", "在某个事件的生命周期中调用，1.doOnEach：当Observable每发送1次数据事件就会调用1次；2.doOnNext：执行Next事件前调用；3.doAfterNext：执行Next事件后调用；4.doOnComplete：Observable正常发送事件完毕后调用；5.doOnError：Observable发送错误事件时调用；6.doOnSubscribe：观察者订阅时调用；7.doAfterTerminate：Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止；8.doFinally：最后执行"));
        operators.add(new Operator("onErrorReturn", "遇到错误时，发送1个特殊事件并正常终止"));
        operators.add(new Operator("onErrorResumeNext", "遇到错误时，发送一个新的被观察者observable，拦截错误Throwable"));
        operators.add(new Operator("onExceptionResumeNext", "遇到错误时，发送一个新的被观察者observable，拦截错误Exception"));
        operators.add(new Operator("retry", "重试，当出现错误时让被观察者重新发射数据，收到onError时重新订阅并重新发射数据"));
        operators.add(new Operator("retryUntil", "类似retry，区别：返回true时不重新发送事件"));
        operators.add(new Operator("retryWhen", "遇到错误时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者（Observable）并发送事件"));
        operators.add(new Operator("repeat", "无条件地、重复发送被观察者事件，可以设置重复创建的次数"));
        operators.add(new Operator("repeatWhen", "有条件重复发送被观察者事件，1：若新被观察者（Observable）返回1个Complete / Error事件，则不重新订阅并发送原来的Observable；2.若新被观察者（Observable）返回其余事件时，则重新订阅并发送原来的Observable"));
        //用一个Item说明下面是过滤操作符
        operators.add(new Operator("", "**************过滤操作符**************↓"));
        //以下是过滤操作符
        operators.add(new Operator("Filter", "过滤特定条件的事件"));
        operators.add(new Operator("ofType", "过滤特定数据类型的数据"));
        operators.add(new Operator("skip/skipLast", "跳过某个事件：使用1：根据顺序跳过数据项；使用2：根据时间跳过数据项"));
        operators.add(new Operator("distinct/distinctUntilChanged", "过滤事件序列中重复的事件 / 连续重复的事件:使用1：过滤事件序列中重复的事件; 使用2：过滤事件序列中 连续重复的事件"));
        operators.add(new Operator("take", "指定观察者最多能接收到的事件数量"));
        operators.add(new Operator("takeLast", "指定观察者只能接收到被观察者发送的最后几个事件"));
        operators.add(new Operator("throttleFirst/throttleLast", "在某段时间内，只发送该段时间内第1次事件/最后1次事件"));
        operators.add(new Operator("Sample", "在某段时间内，只发送该段时间内最新（最后）1次事件，与throttleFirst、throttleLast类似"));
        operators.add(new Operator("throttleWithTimeout/debounce", "发送数据事件时，若2次发送事件的间隔＜指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据"));
        operators.add(new Operator("firstElement/lastElement", "选取发射的第一/最后一个数据"));
        operators.add(new Operator("elementAt", "指定接收某个元素（通过索引值确定）注意：1.elementAt(at) at从0开始索引；2.获取的位置索引 ＞ 发送事件序列长度时，设置默认参数"));
        operators.add(new Operator("elementAtOrError", "在elementAt（）的基础上，当出现越界情况（即获取的位置索引 ＞ 发送事件序列长度）时，即抛出异常"));

    }

    private void itemClickEvent(int position){
        Toast.makeText(Rxjava2Activity.this, position + "", Toast.LENGTH_SHORT).show();
        switch (position){
            case 1:
                startActivity(CreateOperatorActivity.class);
                break;

            case 2:
                startActivity(JustOperatorActivity.class);
                break;

            case 3:
                startActivity(FromArrayOperatorActivity.class);
                break;
            case 4:
                startActivity(FromIterableOperatorActivity.class);
                break;
            case 5:
                startActivity(DeferActivity.class);
                break;
            case 6:
                startActivity(TimerActivity.class);
                break;
            case 7:
                startActivity(IntervalActivity.class);
                break;
            case 8:
                startActivity(IntervalRangeActivity.class);
                break;
            case 9:
                startActivity(RangeActivity.class);
                break;

            case 10:
                startActivity(RangeLongActivity.class);
                break;

            case 12:
                startActivity(MapActivity.class);
                break;

            case 13:
                startActivity(FlatMapActivity.class);
                break;

            case 14:
                startActivity(ConcatMapActivity.class);
                break;

            case 15:
                startActivity(BufferActivity.class);
                break;

            case 16:

                break;

            case 17:
                startActivity(ConcatAndConcatArrayActivity.class);
                break;

            case 18:
                startActivity(MergeAndMergeArrayActivity.class);
                break;

            case 19:
                startActivity(ConcatDelayErrorAndMergeDelayError.class);
                break;

            case 20:
                startActivity(ZipActivity.class);
                break;
            case 21:
                startActivity(CombineLatestActivity.class);
                break;
            case 22:
                startActivity(CombineLatestDelayErrorActivity.class);
                break;
            case 23:
                startActivity(ReduceActivity.class);
                break;
            case 24:
                startActivity(CollectActivity.class);
                break;
            case 25:
                startActivity(StartWithAndStartWithArrayActivity.class);
                break;
            case 26:
                startActivity(CountActivity.class);
                break;
            case 27:

                break;
            case 28:
                startActivity(SubscribeActivity.class);
                break;
            case 29:
                startActivity(SubscribeOnActivity.class);
                break;
            case 30:
                startActivity(ObserveOnActivity.class);
                break;
            case 31:
                startActivity(DelayActivity.class);
                break;
            case 32:
                startActivity(DoActivity.class);
                break;
            case 33:
                startActivity(OnErrorReturnActivity.class);
                break;
            case 34:
                startActivity(OnErrorResumeNextActivity.class);
                break;
            case 35:
                startActivity(OnExceptionResumeNextActivity.class);
                break;
            case 36:
                startActivity(RetryActivity.class);
                break;
            case 37:
                startActivity(RetryUntilActivity.class);
                break;
            case 38:
                startActivity(RetryWhenActivity.class);
                break;
            case 39:
                startActivity(RepeatActivity.class);
                break;
            case 40:
                startActivity(RepeatWhenActivity.class);
                break;
            case 41:

                break;
            case 42:
                startActivity(FilterActivity.class);
                break;
            case 43:
                startActivity(OfTypeActivity.class);
                break;
            case 44:
                startActivity(SkipAndSkipLastActivity.class);
                break;
            case 45:
                startActivity(DistinctAndDistinctUtilChangeActivity.class);
                break;
            case 46:
                startActivity(TakeActivity.class);
                break;
            case 47:
                startActivity(TakeLastActivity.class);
                break;
            case 48:
                startActivity(ThrottleFirstAndThrottleLastActivity.class);
                break;

            case 49:
                startActivity(SampleActivity.class);
                break;

            case 50:
                startActivity(ThrottleWithTimeoutAndDebounceActivity.class);
                break;

            case 51:
                startActivity(FirstElementAndLastElementActivity.class);
                break;

            case 52:
                startActivity(ElementAtActivity.class);
                break;
            case 53:
                startActivity(ElementAtOrErrorActivity.class);
                break;

        }
    }

    private void startActivity(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}

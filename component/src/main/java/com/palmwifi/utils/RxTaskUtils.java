package com.palmwifi.utils;

import com.palmwifi.base.BaseActivity;
import com.palmwifi.base.BaseFragment;
import com.palmwifi.mvp.IView;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by David on 2017/5/5.
 */

public class RxTaskUtils {


    /**
     * 立刻发送到主线程
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void sendMain(LifecycleProvider<T> provider,Action1<Object> action1){
        Observable.empty()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(provider.bindToLifecycle())
                .subscribe(action1);
    }

    /**
     * 立刻发送到异步线程
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void sendAnsyc(LifecycleProvider<T> provider,Action1<Object> action1){
        Observable.empty()
                .observeOn(Schedulers.newThread())
                .compose(provider.bindToLifecycle())
                .subscribe(action1);
    }

    /**
     * 延迟发送到主线程
     * @param delay 时间毫秒
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void delayMain(int delay, LifecycleProvider<T> provider,Action1<Long> action1){
        Observable.timer(delay, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
    }

    /**
     * 延迟发送到异步线程
     * @param delay 时间毫秒
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void delayAsync(int delay, LifecycleProvider<T> provider,Action1<Long> action1){

        Observable.timer(delay, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
    }


    /**
     * 循环发送到主线程
     * @param interval 时间毫秒
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void intervalMain(int interval, int count,LifecycleProvider<T> provider,Action1<Long> action1){
        Observable.interval(interval, TimeUnit.MILLISECONDS)
                .take(count)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
    }

    /**
     * 循环发送到异步线程
     * @param interval 时间毫秒
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void intervalAsync(int interval, int count,LifecycleProvider<T> provider,Action1<Long> action1){

        Observable.interval(interval, TimeUnit.MILLISECONDS)
                .take(count)
                .observeOn(Schedulers.newThread())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
    }






}

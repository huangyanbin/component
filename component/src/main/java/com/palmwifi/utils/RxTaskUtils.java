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
    public static<T> void sendMain(LifecycleProvider<T> provider,Action1<Long> action1){
        delayMain(0,provider,action1);
    }

    /**
     * 立刻发送到异步线程
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void sendAnsyc(LifecycleProvider<T> provider,Action1<Long> action1){
        delayAsync(0,provider,action1);
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
                .subscribeOn(Schedulers.io())
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
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
    }


    /**
     * 循环发送到主线程
     * @param interval 时间毫秒
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void intervalMain(int interval, LifecycleProvider<T> provider,Action1<Long> action1){
        Observable.interval(interval, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
    }

    /**
     * 循环发送到异步线程
     * @param interval 时间毫秒
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void intervalAsync(int interval, LifecycleProvider<T> provider,Action1<Long> action1){

        Observable.interval(interval, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
    }

    /**
     * 循环发送到主线程
     * @param interval 时间毫秒
     * @param count 执行次数
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void intervalMain(int interval, int count,LifecycleProvider<T> provider,Action1<Long> action1){

        final Subscription subscription = Observable.interval(interval, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
        delayMain(count*interval, provider, new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                if(subscription != null && subscription.isUnsubscribed()){
                    subscription.unsubscribe();
                }
            }
        });
    }

    /**
     * 循环发送到主线程
     * @param interval 时间毫秒
     * @param count 执行次数
     * @param provider 绑定对象
     * @param action1 执行任务
     */
    public static<T> void intervalAsync(int interval, int count,LifecycleProvider<T> provider,Action1<Long> action1){

        final Subscription subscription = Observable.interval(interval, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .compose(provider.<Long>bindToLifecycle())
                .subscribe(action1);
        delayAsync(count*interval, provider, new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                if(subscription != null && subscription.isUnsubscribed()){
                    subscription.unsubscribe();
                }
            }
        });
    }


    /**
     * 转换对象
     * @param view
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(IView view) {

        if (view instanceof BaseActivity) {
            return ((BaseActivity) view).bindToLifecycle();
        } else if (view instanceof BaseFragment) {
            return ((BaseFragment) view).bindToLifecycle();
        }else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }
    }
}

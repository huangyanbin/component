package com.palmwifi.utils;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Kiki on 2017/1/17.
 */

public class RxViewUtils {
    /**
     * 防止多次点击
     * @param view
     * @param onNext
     */
    public static void setOnClick(View view, Action1 onNext) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }


    /**
     * 统计点击多少次数
     * @param view
     * @param timeout
     * @param action
     */
    public static void setCountClick(View view,long timeout,Action1<List<Void>> action){
        Observable<Void> observable = RxView.clicks(view).share();
        observable.buffer(observable.debounce(400, TimeUnit.MILLISECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action);
    }

    /**
     * 倒计时
     * @param time
     * @param startAction
     * @param subscriber
     */
    public static void countdown(int time,Action0 startAction,Subscriber<Integer> subscriber){
        if (time < 0) time = 0;
        final int countTime = time;
         Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1).doOnSubscribe(startAction)
                .subscribe(subscriber);
    }

}

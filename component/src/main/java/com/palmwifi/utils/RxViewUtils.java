package com.palmwifi.utils;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Kiki on 2017/1/17.
 */

public class RxViewUtils {
    //防止多次点击
    public static void setView(View view, Action1 onNext) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(onNext);
    }

    public static void setViewForRecord(View view, final Action1 onNext) {

        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(onNext);
    }

    public static void setView(View view, Action1 onNext, Action1 onError) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(onNext, onError);
    }

    public static void setView(View view, Action1 onNext, Action1 onError, Action0 onCompeleted) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(onNext, onError, onCompeleted);
    }

}

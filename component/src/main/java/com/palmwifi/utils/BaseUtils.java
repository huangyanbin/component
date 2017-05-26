package com.palmwifi.utils;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.palmwifi.base.BaseActivity;
import com.palmwifi.base.BaseFragment;
import com.palmwifi.mvp.IView;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by David on 2017/5/26.
 */

public class BaseUtils {

    public static BaseActivity getActivity(IView view){
        if(view != null) {
            if (view instanceof BaseActivity) {
                return (BaseActivity) view;
            } else if (view instanceof BaseFragment) {
                return (BaseActivity) ((BaseFragment) view).getActivity();
            }
        }
        throw new IllegalArgumentException("view isn't activity or fragment");
    }

    public static BaseActivity getActivity(LifecycleProvider provider){
        if(provider != null) {
            if (provider instanceof BaseActivity) {
                return (BaseActivity) provider;
            } else if (provider instanceof BaseFragment) {
                return (BaseActivity) ((BaseFragment) provider).getActivity();
            }
        }
        throw new IllegalArgumentException("provider isn't activity or fragment");
    }

    public static Context getContext(LifecycleProvider provider){
        if(provider != null) {
            if (provider instanceof Activity) {
                return ((Activity) provider).getApplicationContext();
            } else if (provider instanceof Fragment) {
                return ((Fragment)provider).getContext();
            }
        }
        throw new IllegalArgumentException("provider isn't activity or fragment");
    }


    public static <T> LifecycleProvider<T> getProvider(IView view){
        if(view != null) {
            if (view instanceof LifecycleProvider) {
                return (LifecycleProvider<T>) view;
            }
        }
        throw new IllegalArgumentException("view isn't activity or fragment");
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

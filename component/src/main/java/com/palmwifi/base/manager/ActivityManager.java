package com.palmwifi.base.manager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by David on 2017/5/5.
 *  需要对activity 全局进行操作可以重写IActivity
 */

public class ActivityManager implements IActivityLifecycle {

    private IActivityLifecycle mProxy;

    private ActivityManager(){}
    private static volatile ActivityManager instance = null;

    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(Activity activity, @Nullable Bundle savedInstanceState) {
        if(mProxy != null){
            mProxy.onCreate(activity,savedInstanceState);
        }
    }

    @Override
    public void onStart(Activity activity) {
        if(mProxy != null){
            mProxy.onStart(activity);
        }
    }

    @Override
    public void onResume(Activity activity) {
        if(mProxy != null){
            mProxy.onResume(activity);
        }
    }

    @Override
    public void onPause(Activity activity) {
        if(mProxy != null){
            mProxy.onPause(activity);
        }
    }

    @Override
    public void onStop(Activity activity) {
        if(mProxy != null){
            mProxy.onStop(activity);
        }
    }

    @Override
    public void onDestroy(Activity activity) {
        if(mProxy != null){
            mProxy.onDestroy(activity);
        }
    }

    public void setProxy(IActivityLifecycle iActivity) {
        this.mProxy = iActivity;
    }
}

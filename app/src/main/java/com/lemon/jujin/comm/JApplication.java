package com.lemon.jujin.comm;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.palmwifi.base.Component;

/**
 * Created by David on 2017/5/24.
 */

public class JApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Component.init(this,true,"huang");
        Fresco.initialize(this);
    }
}

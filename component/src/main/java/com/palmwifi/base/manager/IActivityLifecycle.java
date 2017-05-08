package com.palmwifi.base.manager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by David on 2017/5/5.
 */

public interface IActivityLifecycle {

     void onCreate(Activity activity, @Nullable Bundle savedInstanceState);

     void onStart(Activity activity);

    void onResume(Activity activity);

    void onPause(Activity activity);

    void onStop(Activity activity);

    void onDestroy(Activity activity);


}

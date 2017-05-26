package com.palmwifi.base;

import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.palmwifi.http.CacheInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by David on 2017/5/26.
 */

public class Component {


    public static void init(Context context, boolean logEnable, String logName) {
        initOkHttpClient(context,logEnable,logName);
        initLogger(logEnable,logName);
    }

    /**
     * 初始化OKHttp
     */
    public static void initOkHttpClient(Context context, boolean logEnable, String logName) {

        File cacheFile = new File(context.getExternalCacheDir(), "httpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (logEnable) {
            builder.addInterceptor(new LoggerInterceptor(logName));
        }
        CacheInterceptor cacheInterceptor = new CacheInterceptor(context);
        OkHttpClient okHttpClient = builder.addNetworkInterceptor(cacheInterceptor)
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .writeTimeout(8000, TimeUnit.MILLISECONDS)
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .addInterceptor(cacheInterceptor)
                .cache(cache)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    public static void initLogger(boolean logEnable, String logName) {
        if (logEnable) {
            Logger.init(logName)                 // default PRETTYLOGGER or use just init()
                    .methodCount(3)                 // default 2
                    .hideThreadInfo()               // default shown
                    .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                    .methodOffset(2); //default AndroidLogAdapter
        } else {
            Logger.init(logName)                 // default PRETTYLOGGER or use just init()
                .logLevel(LogLevel.NONE);       // default LogLevel.FULL

        }
    }


}

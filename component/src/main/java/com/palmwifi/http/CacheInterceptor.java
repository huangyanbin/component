package com.palmwifi.http;

import android.content.Context;
import android.util.Log;

import com.palmwifi.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by David on 2016/10/22.
 * 缓存拦截器 可以配置
 * 没有网络状态 使用缓存
 * 有网络的情况下
 */

public class CacheInterceptor implements Interceptor {
    private Context context;

    public CacheInterceptor(Context context) {
        this.context = context;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (!NetUtils.isNetworkConnected(context)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        return originalResponse.newBuilder()
                .build();

    }

}

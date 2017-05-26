package com.lemon.examination;/**
 * Created by David on 2017/5/5.
 */

import com.lemon.examination.bean.BaseResult;
import com.lemon.examination.bean.User;
import com.palmwifi.http.AsyncCallback;
import com.palmwifi.utils.BaseUtils;
import com.trello.rxlifecycle.LifecycleProvider;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cache.CacheControl;

import java.util.List;

/**
 * <pre>
 *     author : David
 *     e-mail : 873825232@qq.com
 *     time   : 2017/05/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    public static int SUC = 1;

    public HomePresenter(HomeContract.View view) {

        this.view = view;
        this.view.setPresenter(this);
    }



    @Override
    public void requestData() {
        OkHttpUtils.get().url("http:请填入自己的Url").addParams("uid","0").tag(view).build()
                .cacheControl(CacheControl.NO_EXPIRE_USE_CACHE) //设置缓存模式，可不设置
                .cacheControl(5*60).execute(new AsyncCallback<BaseResult<List<User>>,List<User>>(BaseUtils.getProvider(view)) {


            /**
             * 可在异步线程中转换自己想要的对象
             * @param result
             * @return
             */
            @Override
            public List<User> covert(BaseResult<List<User>> result) {
                if(result.getResultCode() == SUC){
                    return result.getT();
                }
                return null;
            }

            @Override
            public void onGetDataSuccess(List<User> data) {
                if(data != null) {
                    view.requestSuccess(data);
                }else {
                    view.requestFailure();
                }
            }

            @Override
            public void onFailure(int responseCode, String response) {
                super.onFailure(responseCode, response);
            }

        });
    }

    /**
     * 解绑
     */
    @Override
    public void unSubscribe() {
        this.view = null;
    }
}
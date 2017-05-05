package com.palmwifi.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.palmwifi.base.rx.RxSupportFragment;
import com.palmwifi.mvp.IPresenter;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by David on 2016/12/1.
 *
 */

public abstract class BaseFragment< P extends IPresenter> extends RxSupportFragment {


    protected P mPresenter;
    protected Unbinder mUnBinder;


    /**
     * 绑定View
     * @param rootView
     */
    protected void bindView(View rootView){

        mUnBinder = ButterKnife.bind(this,rootView);
    }




    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(useEventBus())
            EventBus.getDefault().register(this);
    }



    @Override
    @CallSuper
    public void onDestroy() {
        if(useEventBus())
            EventBus.getDefault().unregister(this);
        OkHttpUtils.getInstance().cancelTag(this);
        if(mUnBinder != null && mUnBinder != Unbinder.EMPTY){
            mUnBinder.unbind();
        }
        if(mPresenter != null)
            mPresenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    @CallSuper
    public void onDetach() {
        super.onDetach();
    }

    protected  boolean useEventBus(){
        return false;
    }

    @Override
    protected int getWindowBackground() {
        return ContextCompat.getColor(getActivity(),android.R.color.transparent);
    }


}

package com.palmwifi.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.palmwifi.base.rx.RxSupportActivity;
import com.palmwifi.fragmention.R;
import com.palmwifi.mvp.IPresenter;
import com.palmwifi.utils.StatusUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by David on 2016/12/1.
 * 在使用Rxjava会经常性的内存泄漏，
 * 使用RxLifeCycle来跟踪回收内存
 */

public abstract class BaseActivity< P extends IPresenter> extends RxSupportActivity {

    protected P mPresenter;

    private Unbinder mUnbinder;





    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(useEventBus()) {
            EventBus.getDefault().register(this);
        }
        StatusUtils.setBarStatusWhite(this,getStatusColor());
        setContentView(setLayoutID());
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
    }
    /**
     * 改变状态栏颜色
     * @return
     */
    protected int getStatusColor(){
        return ContextCompat.getColor(this,android.R.color.white);
    }




    @Override
    @CallSuper
    protected void onDestroy() {
        if(useEventBus())
            EventBus.getDefault().unregister(this);
        OkHttpUtils.getInstance().cancelTag(this);
        if (mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        if(mPresenter != null)
            mPresenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        animationForNew();
    }

    @Override
    public void finish() {
        super.finish();
        animationForOld();
    }

    public void oldFinish() {
        super.finish();
    }

    protected  boolean useEventBus(){
        return false;
    }

    protected abstract int setLayoutID();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    protected abstract void initData();


    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
         return new DefaultHorizontalAnimator();
    }
    public void animationForNew() {
        overridePendingTransition(R.anim.main_translatex100to0,
                R.anim.main_translatex0tof100);
    }
    public void animationForOld() {
        overridePendingTransition(R.anim.main_translatexf100to0,
                R.anim.main_translatex0to100);
    }

    public void startActivityOld(Intent intent) {
        super.startActivity(intent);
    }


}

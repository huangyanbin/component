package com.lemon.examination;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.lemon.examination.bean.User;
import com.palmwifi.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {

    @BindView(R.id.tv_hello)
    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        ButterKnife.bind(this);
    }

    /**
     * 是否开启侧滑关闭
     *
     * @return
     */
    @Override
    protected boolean isOpenSwipe() {
        return false;
    }

    @Override
    protected int setLayoutID() {
        return R.layout.ac_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mPresenter.requestData();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestSuccess(List<User> userList) {
        Toast.makeText(this, "获取到数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestFailure() {
        Toast.makeText(this, "获取到数据失败", Toast.LENGTH_SHORT).show();
    }
}

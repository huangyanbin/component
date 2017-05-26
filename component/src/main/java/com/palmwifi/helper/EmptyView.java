package com.palmwifi.helper;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.palmwifi.fragmention.R;


/**
 * Created by David on 2016/8/8.
 * 加载,空白提示,网络错误重新加载通用类
 * 为了适用网络请求加载loading，实现了ILoading接口
 */

public class EmptyView  {

    public static final int HIDE = 0;
    public static final int LOADING = 1;
    public static final int FAILURE = 2;
    public static final int NO_DATA = 3;
    private View contentView;
    private View emptyView;
    private TextView emptyTipTv;
    private View emptyTipPb;
    private Builder builder;
    public int status;

    private EmptyView(Builder builder) {
        this.builder = builder;
        contentView = builder.contentView;
        emptyView = View.inflate(contentView.getContext(), R.layout.layout_empty, null);
        emptyTipTv = (TextView) emptyView.findViewById(R.id.tv_empty_tip);
        emptyTipPb =  emptyView.findViewById(R.id.pb_empty_tip);
        genEmptyView();
        emptyView.setVisibility(View.GONE);
        status = HIDE;
    }


    public void showLoading() {
        if (emptyView != null) {
           emptyTipTv.setText(builder.loadingTip != null ? builder.loadingTip
                    : contentView.getContext().getString(R.string.empty_loading_tip));
           emptyTipPb.setVisibility(View.VISIBLE);
            emptyView.setClickable(false);
        }
        status = LOADING;
        show();
    }

    public void showFailure() {

        if (emptyView != null) {
           emptyTipTv.setText(builder.failureTip != null ? builder.failureTip
                    : contentView.getContext().getString(R.string.empty_loading_failure_tip));
           emptyTipPb.setVisibility(View.INVISIBLE);
            if(builder.onFailureClickListener != null){
                emptyView.setClickable(true);
                emptyView.setOnClickListener(builder.onFailureClickListener);
            }
        }
        status = FAILURE;
        show();
    }

    public void showNoData() {

        if (emptyView != null) {
           emptyTipTv.setText(builder.noDataTip != null ? builder.noDataTip
                    : contentView.getContext().getString(R.string.empty_loading_no_data_tip));
            emptyView.setClickable(false);
           emptyTipPb.setVisibility(View.INVISIBLE);
        }
        status = NO_DATA;
        show();
    }


    private void show() {
        if (emptyView != null) {
            emptyView.setVisibility(View.VISIBLE);
        }
        if (contentView != null) {
            contentView.setVisibility(View.GONE);
        }
    }

    public void hide() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
        if (contentView != null) {
            contentView.setVisibility(View.VISIBLE);
        }
        status = HIDE;
    }

    public void startLoading() {
        if(status != LOADING) {
            showLoading();
        }
    }

    public void stopLoading() {
        if(status == LOADING) {
            hide();
        }
    }

    public void onProgress(float progress) {

    }


    private void genEmptyView() {
        ViewParent parent = contentView.getParent();
        if(parent != null) {
            if (parent instanceof ViewGroup) {
                if (parent instanceof LinearLayout) {
                    ((LinearLayout) parent).addView(emptyView);
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) emptyView.getLayoutParams();
                    lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp.height = -1;
                    lp.gravity = Gravity.CENTER;
                    emptyView.setLayoutParams(lp);
                } else if (parent instanceof RelativeLayout) {
                    ((ViewGroup) parent).addView(emptyView);
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) emptyView.getLayoutParams();
                    lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                    emptyView.setLayoutParams(lp);
                } else if (parent instanceof FrameLayout) {
                    ((ViewGroup) parent).addView(emptyView);
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) emptyView.getLayoutParams();
                    lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp.height = -1;
                    lp.gravity = Gravity.CENTER;
                    emptyView.setLayoutParams(lp);
                }
            }
        }
    }


    public static class Builder {
        View contentView;
        String loadingTip;
        String failureTip;
        String noDataTip;
        View.OnClickListener onFailureClickListener;
        private boolean isOffset;

        public Builder(View contentView) {

            this.contentView = contentView;
        }

        public Builder setLoadingTip(String loadingTip) {
            this.loadingTip = loadingTip;
            return this;
        }

        public Builder setFailureTip(String failureTip) {
            this.failureTip = failureTip;
            return this;
        }

        public Builder setNoDataTip(String noDataTip) {
            this.noDataTip = noDataTip;
            return this;
        }

        public Builder setIsOffest(boolean isOffset) {
            this.isOffset = isOffset;
            return this;
        }

        public EmptyView build() {
            return new EmptyView(this);
        }

        public Builder setOnFailureClickListener(View.OnClickListener onFailureClickListener) {
            this.onFailureClickListener = onFailureClickListener;
            return this;
        }
    }
}

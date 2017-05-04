package com.palmwifi.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.palmwifi.fragmention.R;


/**
 * Created by huang on 2016/9/21.
 * 抽出RecyclerView emptyView
 */

public class RecyclerEmptyView implements ILoading {

    private Builder builder;
    private TextView tipTv;
    private ImageView tipImg;
    private View tipPb;


    public static class Builder {
        private Context context;
        private RecyclerView recyclerView;
        private BaseQuickAdapter adapter;
        private String emptyTip;
        private String failureTip;
        private String loadingTip;
        private String noMoreTip;
        private String loadMoreTip;
        private int emptyIcon;
        private int failureIcon;
        private int loadingIcon;
        private View.OnClickListener onFailureClickListener;
        private boolean isFullScreen = true;


        public Builder(Context context, RecyclerView recyclerView) {
            this.context = context;
            this.recyclerView = recyclerView;
            this.adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        }


        public Builder setEmptyTip(String emptyTip) {
            this.emptyTip = emptyTip;
            return this;
        }

        public Builder setFailureTip(String failureTip) {
            this.failureTip = failureTip;
            return this;
        }

        public Builder setFailureIcon(int failureIcon) {
            this.failureIcon = failureIcon;
            return this;
        }

        public Builder setLoadingIcon(int loadingIcon) {
            this.loadingIcon = loadingIcon;
            return this;
        }

        public Builder setEmptyIcon(int emptyIcon) {
            this.emptyIcon = emptyIcon;
            return this;
        }

//        public Builder setSize(int height,int width) {
//            return this;
//        }


        public Builder setNoMoreTip(String noMoreTip) {
            this.noMoreTip = noMoreTip;
            return this;
        }

        public Builder setLoadingTip(String loadingTip) {
            this.loadingTip = loadingTip;
            return this;
        }

//        public Builder setLoadMoreTip(String loadMoreTip) {
//            this.loadMoreTip = loadMoreTip;
//            return this;
//        }

        public Builder setOnFailureClickListener(View.OnClickListener onFailureClickListener) {
            this.onFailureClickListener = onFailureClickListener;
            return this;
        }

        public Builder setFullScreen(boolean fullScreen) {
            isFullScreen = fullScreen;
            return this;
        }

        public RecyclerEmptyView create() {
            return new RecyclerEmptyView(this);
        }

    }

    public Builder getBuilder() {
        return builder;
    }

    private RecyclerEmptyView(Builder builder) {
        this.builder = builder;
    }

    @Override
    public void startLoading() {
        View emptyView = getEmptyView();
        if (builder.loadingIcon != 0) {
            tipImg.setVisibility(View.VISIBLE);
            tipImg.setImageResource(builder.loadingIcon);
        } else {
            tipImg.setVisibility(View.INVISIBLE);
        }
        tipTv.setText(builder.loadingTip == null ? builder.context.getString(R.string.empty_loading_tip) : builder.loadingTip);
        emptyView.setClickable(false);
        tipPb.setVisibility(View.VISIBLE);
        changedView(emptyView);
    }

    @Override
    public void stopLoading() {
        //这里不需要实现
    }


    @Override
    public void onProgress(float progress) {

    }

    public void showFailure() {

        View emptyView = getEmptyView();
        if (builder.failureIcon != 0) {
            tipImg.setVisibility(View.VISIBLE);
            tipImg.setImageResource(builder.failureIcon);
        } else {
            tipImg.setVisibility(View.INVISIBLE);
        }
        tipTv.setText(builder.failureTip == null ? builder.context.getString(R.string.empty_loading_failure_tip) : builder.failureTip);
        tipPb.setVisibility(View.INVISIBLE);
        emptyView.setClickable(true);
        if (builder.onFailureClickListener != null) {
            emptyView.setOnClickListener(builder.onFailureClickListener);
        }
        changedView(emptyView);

    }


    @Override
    public void showNoData() {

        View emptyView = getEmptyView();
        if (builder.emptyIcon != 0) {
            tipImg.setVisibility(View.VISIBLE);
            tipImg.setImageResource(builder.emptyIcon);
        } else {
            tipImg.setVisibility(View.INVISIBLE);
        }
        tipTv.setText(builder.emptyTip == null ? builder.context.getString(R.string.empty_loading_no_data_tip) : builder.emptyTip);
        emptyView.setClickable(false);
        tipPb.setVisibility(View.INVISIBLE);
        changedView(emptyView);

    }

    public void showNoDataAndNoTip() {

        View emptyView = getEmptyView();
        if (builder.emptyIcon != 0) {
            tipImg.setVisibility(View.VISIBLE);
            tipImg.setImageResource(builder.emptyIcon);
        } else {
            tipImg.setVisibility(View.INVISIBLE);
        }
        tipImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        emptyView.setClickable(false);
        tipPb.setVisibility(View.INVISIBLE);
        tipTv.setVisibility(View.GONE);
        changedView(emptyView);
    }

    /**
     * 显示没有更多
     */
    public void showNoMore() {

        if (builder.adapter.getFooterLayoutCount() == 0) {
            TextView noMoreTv = (TextView) View.inflate(builder.context, R.layout.item_no_more, null);
            if (builder.noMoreTip != null) {
                noMoreTv.setText(builder.noMoreTip);
            }
            builder.adapter.addFooterView(noMoreTv);
        }
    }

    /**
     * 上拉加载更多
     */
    public void showLoadMore() {

        if (builder.adapter.getFooterLayoutCount() == 0) {
            TextView noMoreTv = (TextView) View.inflate(builder.context, R.layout.item_no_more, null);
            if (builder.loadMoreTip != null) {
                noMoreTv.setText(builder.loadMoreTip);
            }
            builder.adapter.addFooterView(noMoreTv);
        }
    }

    private View getEmptyView() {

        LayoutInflater inflater = (LayoutInflater) builder.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View emptyView = inflater.inflate(R.layout.layout_empty, (ViewGroup) builder.recyclerView.getParent(), false);
        if (builder.isFullScreen) {
            emptyView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT));
        } else {
            emptyView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT));
        }

        tipTv = (TextView) emptyView.findViewById(R.id.tv_empty_tip);
        tipPb = emptyView.findViewById(R.id.pb_empty_tip);
        tipImg = (ImageView) emptyView.findViewById(R.id.img_empty_tip);

        return emptyView;
    }


    private void changedView(View view) {

        builder.adapter.setEmptyView(view);

    }
}
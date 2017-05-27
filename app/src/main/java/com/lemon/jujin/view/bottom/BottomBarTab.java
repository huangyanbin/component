package com.lemon.jujin.view.bottom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemon.jujin.R;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView titleTv;
    private ImageView pointView;
    private Context mContext;
    private int mTabPosition = -1;
    private boolean isShowPoint;

    public BottomBarTab(Context context, @DrawableRes int icon) {
        this(context, null, icon);
    }

    public BottomBarTab(Context context, @DrawableRes int icon, String title) {
        super(context, null, 0);
        init(context, icon, title, false);
    }

    public BottomBarTab(Context context, @DrawableRes int icon, String title, boolean isNeedPoint) {
        super(context, null, 0);
        init(context, icon, title, isNeedPoint);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon) {
        this(context, attrs, 0, icon);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon) {
        super(context, attrs, defStyleAttr);
        init(context, icon, null, false);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context, int icon, String title, boolean isNeedPoint) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.menu_item, null);
        titleTv = (TextView) view.findViewById(R.id.menu_title_tv);
        titleTv.setText(title);
        mIcon = (ImageView) view.findViewById(R.id.menu_icon_iv);
        mIcon.setImageResource(icon);
        mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect));
        if (isNeedPoint) {
            pointView = (ImageView) view.findViewById(R.id.menu_news_point);
        }
        addView(view);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_select));
            titleTv.setTextColor(mContext.getResources().getColor(R.color.tab_select));
        } else {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
            titleTv.setTextColor(mContext.getResources().getColor(R.color.tab_unselect));
        }
    }

    public void setPointView(boolean isShow) {
        isShowPoint = isShow;
        if (pointView != null) {
            pointView.setVisibility(isShow ? VISIBLE : GONE);
            invalidate();
        }
    }

    public void recyclerPointView(boolean isShow) {

        if (pointView != null) {
            pointView.setVisibility(isShow & isShowPoint ? VISIBLE : GONE);
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}

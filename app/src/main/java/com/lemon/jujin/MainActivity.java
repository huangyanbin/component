package com.lemon.jujin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.lemon.jujin.view.bottom.BottomBar;
import com.lemon.jujin.view.bottom.BottomBarTab;
import com.palmwifi.base.BaseActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity implements LazyMainFragment.OnBackToFirstListener {

    private SupportFragment[] mFragments = new SupportFragment[4];
    private BottomBar mBottomBar;
    public static final int VIDEO_TAG = 0;
    private static final int DIS_TAG = 1;
    private static final int VIP_TAG = 2;
    private static final int MY_TAG = 3;

    @Override
    protected int setLayoutID() {
        return R.layout.activity_home;
    }


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            mFragments[VIDEO_TAG] = HomeFragment.newInstance();
            mFragments[DIS_TAG] = SearchFragment.newInstance();
            mFragments[VIP_TAG] = NotifyFragment.newInstance();
            mFragments[MY_TAG] = UserFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container, VIDEO_TAG,
                    mFragments[VIDEO_TAG],
                    mFragments[DIS_TAG],
                    mFragments[VIP_TAG],
                    mFragments[MY_TAG]);
        } else {
            mFragments[VIDEO_TAG] = findFragment(HomeFragment.class);
            mFragments[DIS_TAG] = findFragment(SearchFragment.class);
            mFragments[VIP_TAG] = findFragment(NotifyFragment.class);
            mFragments[MY_TAG] = findFragment(UserFragment.class);
        }
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.home))
                .addItem(new BottomBarTab(this, R.mipmap.search))
                .addItem(new BottomBarTab(this, R.mipmap.notify))
                .addItem(new BottomBarTab(this, R.mipmap.user));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
                if (count > 1) {
                    if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof SearchFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof NotifyFragment) {
                        currentFragment.popToChild(NotifyFragment.class, false);
                    } else if (currentFragment instanceof UserFragment) {
                        currentFragment.popToChild(UserFragment.class, false);
                    }
                    return;
                }
            }
        });

    }


    @Override
    protected boolean isOpenSwipe() {
        return false;
    }


    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }



    private long mExitTime;

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void finish() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getApplicationContext(), String.format(getString(R.string.exit_tip),getString(R.string.app_name)), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            oldFinish();
        }
    }
}

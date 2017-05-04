package com.palmwifi.view.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by David on 2016/11/16.
 */

public class ScrollTopUtils {

    public static final int MAX_SCROLL_POSITION = 20;

    public static void scrollTop(RecyclerView recyclerView){
        if(recyclerView != null){
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if(layoutManager != null && layoutManager instanceof LinearLayoutManager){
                LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                if(firstItemPosition < MAX_SCROLL_POSITION) {
                    recyclerView.smoothScrollToPosition(0);
                }else{
                    recyclerView.scrollToPosition(MAX_SCROLL_POSITION);
                    recyclerView.smoothScrollToPosition(0);
                }
            }

        }
    }

    public static boolean scrollTop(RecyclerView recyclerView, int scrollPosition){
        boolean isScroll = false;
        if(recyclerView != null){
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if(layoutManager != null && layoutManager instanceof LinearLayoutManager){
                LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    isScroll = firstItemPosition > 0 ;
                if(firstItemPosition < scrollPosition) {
                    recyclerView.smoothScrollToPosition(0);
                }else{
                    recyclerView.scrollToPosition(scrollPosition);
                    recyclerView.smoothScrollToPosition(0);
                }
            }

        }
        return isScroll;
    }



}

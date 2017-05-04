package com.palmwifi.view.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 常用RecyclerView分割线
 */
public abstract class BaseItemDecoration extends RecyclerView.ItemDecoration {

    protected   RecyclerView.Adapter mAdapter;


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if(mAdapter == null) {
            mAdapter = parent.getAdapter();
        }
        int type  = mAdapter.getItemViewType(position);
        final int spanCount = getSpanCount(parent);
        boolean isFirstColumn = isPinnedChildType(type) && isFirstColumn(parent, position, spanCount);
        getItemOffsets(outRect,position,type,isFirstColumn);
    }

    public abstract void getItemOffsets(Rect outRect,int position,int type,boolean isFirstColumn);


   /* @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            int type  = mAdapter.getItemViewType(position +1);
            if(type == HomeAdapter.TILTE_TYPE) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + 2*space;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
*/


    /**
     * 适用于网格布局，用于判断是否是第一列
     *
     * @param parent
     * @param pos
     * @param spanCount
     * @return
     */
    private boolean isFirstColumn(RecyclerView parent, int pos, int spanCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager.SpanSizeLookup lookup = ((GridLayoutManager)layoutManager).getSpanSizeLookup();
            int spanSize = lookup.getSpanSize(pos);
            final int headerPosition = findPinnedHeaderPosition(pos);
            if (headerPosition >= 0 && (pos - (headerPosition + 1))*spanSize % spanCount == 0) {
                // 找到头部位置减去包括头部位置之前的个数
                return true;
            }
        }
        return false;
    }

    /**
     * 从传入位置递减找出标签的位置
     *
     * @param formPosition
     * @return
     */
    private int findPinnedHeaderPosition(int formPosition) {

        for (int position = formPosition; position >= 0; position--) {
            // 位置递减，只要查到位置是标签，立即返回此位置
            final int type = mAdapter.getItemViewType(position);
            if (isPinnedHeaderType(type)) {
                return position;
            }
        }

        return -1;
    }

    /**
     * 通过适配器告知类型是否为标签
     *
     * @param type
     * @return
     */
    public abstract boolean isPinnedHeaderType(int type);

    /**
     * 是否是child类型
     * @param type
     * @return
     */

    public abstract boolean isPinnedChildType(int type);

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
}

package com.palmwifi.view.recyclerview;


import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by David on 2016/11/17.
 * 实现多种不同布局item必须实现该接口
 */

public interface IRecyclerItem extends MultiItemEntity {


    /**
     * 一行的数量
     * @return
     */
    public int getSpanSize();
}

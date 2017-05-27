package com.lemon.jujin.adapter;

import android.net.Uri;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lemon.jujin.R;
import com.lemon.jujin.entry.Article;
import com.lemon.jujin.utils.TimeUtils;

import java.util.List;

/**
 * Created by David on 2017/5/24.
 */

public class HomeAdapter extends BaseQuickAdapter<Article,BaseViewHolder> {
    public HomeAdapter(List<Article> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.content,item.getContent()).setText(R.id.title,item.getTitle())
                .setText(R.id.tv_user_name,item.getUser().getNickName()== null? item.getUser().getUserName():item.getUser().getNickName()).setText(R.id.tv_type,item.getType().getTitle())
            .setText(R.id.tv_time, TimeUtils.getShowTimeString(item.getCreateTime()))
            .setText(R.id.tv_comment_count,item.getCommentCount()+"")
            .setText(R.id.tv_fav_count,item.getFavCount()+"").getView(R.id.img_fav).setSelected(item.getLike() == 1);
        SimpleDraweeView draweeView = helper.getView(R.id.img_user_icon);
        if(item.getUser().getIcon() != null) {
            Uri uri = Uri.parse(item.getUser().getIcon());
            draweeView.setImageURI(uri);//之后的一切全交给fresco就行了
        }
    }
}

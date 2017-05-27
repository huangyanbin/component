package com.lemon.jujin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemon.jujin.adapter.HomeAdapter;
import com.lemon.jujin.comm.ConstactURL;
import com.lemon.jujin.entry.Article;
import com.lemon.jujin.entry.Result;
import com.lemon.jujin.entry.ResultCode;
import com.palmwifi.helper.RecyclerEmptyView;
import com.palmwifi.http.JsonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by David on 2017/5/27.
 */

public class SearchFragment extends LazyMainFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    HomeAdapter mAdapter;
    RecyclerEmptyView emptyView;

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_home,container,false);
        bindView(rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        mAdapter = new HomeAdapter(new ArrayList<Article>());
        recyclerView.setAdapter(mAdapter);
        emptyView = new RecyclerEmptyView.Builder(getActivity(),recyclerView).create();
        emptyView.startLoading();
        OkHttpUtils.get().tag(this).url(ConstactURL.HOST+"/article/list/1/5?uid=2").build()
                .execute(new JsonCallback<Result<List<Article>>>(this) {
                    @Override
                    public void onGetDataSuccess(Result<List<Article>> result) {
                        if(result.getCode() == ResultCode.SUC){
                            mAdapter.addData(result.getData());
                        }else{
                            emptyView.showFailure();
                        }
                    }
                });
        return rootView;
    }
}

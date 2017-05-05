package com.lemon.examination;

import com.lemon.examination.bean.User;
import com.palmwifi.mvp.IPresenter;
import com.palmwifi.mvp.IView;

import java.util.List;

/**
 * <pre>
 *     author : David
 *     e-mail : 873825232@qq.com
 *     time   : 2017/05/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface HomeContract {

    /**
     * 回调View
     */
    interface View extends IView<Presenter> {

        void requestSuccess(List<User> userList);
        void requestFailure();
    }

    /**
     * 责任层
     */
    interface Presenter extends IPresenter {
       void requestData();
    }
}
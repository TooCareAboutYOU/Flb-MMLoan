package com.mmkjflb.mmloan.viewmodel.baseinform;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.BaseInfoEntity;

import java.util.Map;

public interface BaseInformContract {
    interface View extends BaseView {

        void baseinfoSuc(BaseBean baseBean);

        void getBaseInfoSuc(BaseBean<BaseInfoEntity> baseBean);

        void getQiniuTokenSuc(BaseBean<String> baseBean);
    }

    interface Presenter extends BasePresenter<BaseInformContract.View> {
        void getQiniuToken(String sessionId);

        void baseinfo(Map<String, Object> map);

        void getBaseInfo(String sessionId);
    }
}

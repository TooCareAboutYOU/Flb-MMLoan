package com.mmkjflb.mmloan.viewmodel.assetinform;

import com.mmkjflb.mmloan.model.entity.AssertsInfoEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface AssetInformContract {
    interface View extends BaseView {
        void assertsinfoSuc(BaseBean baseBean);
        void getAssertsInfoSuc(BaseBean<AssertsInfoEntity> baseBean);
        void getQiniuTokenSuc(BaseBean<String> baseBean);
    }

    interface Presenter extends BasePresenter<AssetInformContract.View> {
        void assertsinfo(Map<String, Object> partMap);
        void getAssertsInfo( String sessionId);
        void getQiniuToken(String sessionId);
    }
}

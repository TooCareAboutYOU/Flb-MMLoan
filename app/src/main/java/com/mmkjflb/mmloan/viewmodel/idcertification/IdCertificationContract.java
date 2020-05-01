package com.mmkjflb.mmloan.viewmodel.idcertification;

import com.mmkjflb.mmloan.model.entity.BaseInfoEntity;
import com.mmkjflb.mmloan.model.entity.IdCardCheckEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IdCertificationContract {
    interface View extends BaseView {
        void getBaseInfoSuc(BaseBean<BaseInfoEntity> baseBean) throws ExecutionException, InterruptedException;

        void idCardCheckSuc(BaseBean<IdCardCheckEntity> baseBean);

        void idcheckSuc(BaseBean baseBean);
        void getQiniuTokenSuc(BaseBean<String> baseBean);

    }

    interface Presenter extends BasePresenter<IdCertificationContract.View> {
        void getBaseInfo(String sessionId);
        void getQiniuToken(String sessionId);

        void idCardCheck(Map<String, RequestBody> partMap, MultipartBody.Part multipartPart);

        void idcheck(Map<String, Object> request);

    }
}

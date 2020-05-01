package com.mmkjflb.mmloan.viewmodel.bankrepay;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.CertificateDetailEntity;
import com.mmkjflb.mmloan.model.entity.CollectionAccountEntity;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/28.
 */

public class BankRepayContract {
    interface View extends BaseView {
        void certificateDetailSuc(BaseBean<CertificateDetailEntity> baseBean);
        void collectionAccountSuc(BaseBean<CollectionAccountEntity> baseBean);
        void certificateUploadSuc(BaseBean baseBean);
        void getQiniuTokenSuc(BaseBean<String> baseBean);
    }

    interface Presenter extends BasePresenter<BankRepayContract.View> {
        void certificateDetail(String sessionId, int bidId, int billId);
        void collectionAccount(String sessionId);
        void certificateUpload(Map<String, Object> map);
        void getQiniuToken(String sessionId);
    }
}

package com.mmkjflb.mmloan.viewmodel.fragment.student;

import com.mmkjflb.mmloan.model.entity.StudentsEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by
 * Date: On 2018/3/9
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public interface StudentsContract {

    interface View extends BaseView {
        void studentinfoSuc(BaseBean baseBean);
        void getStudentInfoSuc(BaseBean<StudentsEntity> baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void studentinfo(Map<String, RequestBody> params, MultipartBody.Part studentCard);
        void getStudentInfo(String sessionId);
    }
}

package com.mmkjflb.mmloan.viewmodel.fragment.payroll;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.CompanyNatureEntity;
import com.mmkjflb.mmloan.model.entity.SalariesEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by
 * Date: On 2018/3/8
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public interface PayrollContract {

    interface View extends BaseView {
        void getQiniuTokenSuc(BaseBean<String> baseBean);

        void salariesinfoSuc(BaseBean baseBean);

        void getSalariesInfoSuc(BaseBean<SalariesEntity> baseBean);

        void getCompanyNatureSuc(BaseBean<List<CompanyNatureEntity>> baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void salariesinfo(Map<String, Object> params);

        void getSalariesInfo(String sessionId);

        void getCompanyNature();

        void getQiniuToken(String sessionId);
    }
}

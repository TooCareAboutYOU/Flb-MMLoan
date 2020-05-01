package com.mmkjflb.mmloan.di.component;

import android.app.Activity;

import com.mmkjflb.mmloan.di.module.ActivityModule;
import com.mmkjflb.mmloan.di.scope.ActivityScope;
import com.mmkjflb.mmloan.viewmodel.active.ActiveActivity;
import com.mmkjflb.mmloan.viewmodel.assetinform.AssetInformActivity;
import com.mmkjflb.mmloan.viewmodel.bankcard.BankCardActivity;
import com.mmkjflb.mmloan.viewmodel.bankinform.BankInformActivity;
import com.mmkjflb.mmloan.viewmodel.bankrepay.BankRepayActivity;
import com.mmkjflb.mmloan.viewmodel.baseinform.BaseInformActivity;
import com.mmkjflb.mmloan.viewmodel.bindgcash.BindGCashActivity;
import com.mmkjflb.mmloan.viewmodel.certificateresult.CertificationResultActivity;
import com.mmkjflb.mmloan.viewmodel.certificationmain.CertificationMainActivity;
import com.mmkjflb.mmloan.viewmodel.changepassword.ChangePasswordActivity;
import com.mmkjflb.mmloan.viewmodel.changephone.ChangePhoneOneActivity;
import com.mmkjflb.mmloan.viewmodel.changephone.ChangePhoneTwoActivity;
import com.mmkjflb.mmloan.viewmodel.chooseposition.ChoosePositionActivity;
import com.mmkjflb.mmloan.viewmodel.emergencycontact.EmergencyContactActivity;
import com.mmkjflb.mmloan.viewmodel.enploymentinformation.EnploymentinformationActivity;
import com.mmkjflb.mmloan.viewmodel.extracredit.ExtraCreditActivity;
import com.mmkjflb.mmloan.viewmodel.feedback.FeedbackActivity;
import com.mmkjflb.mmloan.viewmodel.forgetpassword.ForgetPwdActivity;
import com.mmkjflb.mmloan.viewmodel.guide.GuideActivity;
import com.mmkjflb.mmloan.viewmodel.idcertification.IdCertificationActivity;
import com.mmkjflb.mmloan.viewmodel.loan.LoanActivity;
import com.mmkjflb.mmloan.viewmodel.login.LoginActivity;
import com.mmkjflb.mmloan.viewmodel.maimaiborrow.MaiMaiBorrowActivity;
import com.mmkjflb.mmloan.viewmodel.main.MainActivity;
import com.mmkjflb.mmloan.viewmodel.modifygcash.ModifyGCashActivity;
import com.mmkjflb.mmloan.viewmodel.paymentcode.PaymentCodeActivity;
import com.mmkjflb.mmloan.viewmodel.refundcode.RefundCodeActivity;
import com.mmkjflb.mmloan.viewmodel.refundeposit.RefundDepositActivity;
import com.mmkjflb.mmloan.viewmodel.refundresult.RefundResultActivity;
import com.mmkjflb.mmloan.viewmodel.register.RegisterActivity;
import com.mmkjflb.mmloan.viewmodel.repay.RepayActivity;
import com.mmkjflb.mmloan.viewmodel.repaychannel.RepayChannelsActivity;
import com.mmkjflb.mmloan.viewmodel.socialinform.SocialInformActivity;
import com.mmkjflb.mmloan.viewmodel.splash.SplashActivity;
import com.mmkjflb.mmloan.viewmodel.withdraw.WithDrawActivity;

import dagger.Component;



@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(WithDrawActivity withDrawActivity);
    void inject(ModifyGCashActivity modifyGCashActivity);
    void inject(BindGCashActivity bindGCashActivity);

    void inject(ChoosePositionActivity choosePositionActivity);

    void inject(RefundDepositActivity refundDepositActivity);

    void inject(RefundResultActivity returnResultActivity);

    void inject(RefundCodeActivity refundCodeActivity);

    void inject(ChangePhoneOneActivity changePhoneOneActivity);

    void inject(ChangePhoneTwoActivity changePhoneTwoActivity);


    void inject(MainActivity mainActivity);

    void inject(CertificationMainActivity certificationMainActivity);

    void inject(CertificationResultActivity certificationResultActivity);

    void inject(MaiMaiBorrowActivity maiMaiBorrowActivity);


    void inject(SplashActivity splashActivity);

    void inject(GuideActivity guideActivity);


    void inject(BankInformActivity bankInformActivity);

    void inject(AssetInformActivity assetInformActivity);

    void inject(BaseInformActivity baseInformActivity);

    void inject(LoginActivity loginActivity);


    void inject(ForgetPwdActivity forgetPwdActivity);

    void inject(RegisterActivity registerActivity);


    void inject(LoanActivity loanActivity);

    void inject(RepayActivity repayActivity);

    void inject(RepayChannelsActivity RepayChannelsActivity);

    void inject(BankCardActivity bankCardActivity);


    void inject(SocialInformActivity socialInformActivity);

    void inject(ChangePasswordActivity changePasswordActivity);

    void inject(FeedbackActivity feedbackActivity);


    void inject(IdCertificationActivity idCertificationActivity);


    void inject(EmergencyContactActivity emergencyContactActivity);


    void inject(ActiveActivity activeActivity);//无效页面


    void inject(EnploymentinformationActivity enploymentinformation);

    void inject(PaymentCodeActivity paymentCodeActivity);

    void inject(BankRepayActivity bankRepayActivity);

    void inject(ExtraCreditActivity extraCreditActivity);

}

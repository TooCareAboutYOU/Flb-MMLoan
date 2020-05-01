package com.mmkjflb.mmloan.model;

import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkjflb.mmloan.model.entity.AdviseEntity;
import com.mmkjflb.mmloan.model.entity.AmountTypeEntity;
import com.mmkjflb.mmloan.model.entity.ApplyForPromoteEntity;
import com.mmkjflb.mmloan.model.entity.ArtificialAuthentStatusEntity;
import com.mmkjflb.mmloan.model.entity.AssertsInfoEntity;
import com.mmkjflb.mmloan.model.entity.AuditDepositEntity;
import com.mmkjflb.mmloan.model.entity.AuthEntity;
import com.mmkjflb.mmloan.model.entity.AuthentStatusEntity;
import com.mmkjflb.mmloan.model.entity.BankInfoEntity;
import com.mmkjflb.mmloan.model.entity.BankListEntity;
import com.mmkjflb.mmloan.model.entity.BankTypeEntity;
import com.mmkjflb.mmloan.model.entity.BaseInfoEntity;
import com.mmkjflb.mmloan.model.entity.BindCardEntity;
import com.mmkjflb.mmloan.model.entity.BooleanEntity;
import com.mmkjflb.mmloan.model.entity.CardManagerEntity;
import com.mmkjflb.mmloan.model.entity.CashDepositInfoEntity;
import com.mmkjflb.mmloan.model.entity.CertificateDetailEntity;
import com.mmkjflb.mmloan.model.entity.ChannelGoogleEntity;
import com.mmkjflb.mmloan.model.entity.CollectionAccountEntity;
import com.mmkjflb.mmloan.model.entity.CompanyNatureEntity;
import com.mmkjflb.mmloan.model.entity.ContactsEntity;
import com.mmkjflb.mmloan.model.entity.CouponEntity;
import com.mmkjflb.mmloan.model.entity.DepositCodeEntity;
import com.mmkjflb.mmloan.model.entity.ExtraCreditBean;
import com.mmkjflb.mmloan.model.entity.FreelanceEntity;
import com.mmkjflb.mmloan.model.entity.GuideEntity;
import com.mmkjflb.mmloan.model.entity.IdCardCheckEntity;
import com.mmkjflb.mmloan.model.entity.IsPaidEntity;
import com.mmkjflb.mmloan.model.entity.IslandProvinceCityEntity;
import com.mmkjflb.mmloan.model.entity.LoanEntity;
import com.mmkjflb.mmloan.model.entity.LoginBean;
import com.mmkjflb.mmloan.model.entity.MainEntity;
import com.mmkjflb.mmloan.model.entity.MyBorrowBean;
import com.mmkjflb.mmloan.model.entity.NoticeEntity;
import com.mmkjflb.mmloan.model.entity.OcrCheckEntity;
import com.mmkjflb.mmloan.model.entity.PayCodeEntity;
import com.mmkjflb.mmloan.model.entity.PaymentForFasPayEntity;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;
import com.mmkjflb.mmloan.model.entity.PositionEntity;
import com.mmkjflb.mmloan.model.entity.PreventionEntity;
import com.mmkjflb.mmloan.model.entity.PrivateOwnerEntity;
import com.mmkjflb.mmloan.model.entity.ProductInfoBean;
import com.mmkjflb.mmloan.model.entity.QueryActivityEntity;
import com.mmkjflb.mmloan.model.entity.QueryInviteActivityEntity;
import com.mmkjflb.mmloan.model.entity.QueryPaymentChannelEntity;
import com.mmkjflb.mmloan.model.entity.QueryProductInfoEntity;
import com.mmkjflb.mmloan.model.entity.QueryRefundInfoEntity;
import com.mmkjflb.mmloan.model.entity.ReauthenticationEntity;
import com.mmkjflb.mmloan.model.entity.RechargeEntity;
import com.mmkjflb.mmloan.model.entity.RecordEntity;
import com.mmkjflb.mmloan.model.entity.RefundCodeEntity;
import com.mmkjflb.mmloan.model.entity.RefundDepositEntity;
import com.mmkjflb.mmloan.model.entity.RefundInfoEntity;
import com.mmkjflb.mmloan.model.entity.RepayEntity;
import com.mmkjflb.mmloan.model.entity.RepaymentShopEntity;
import com.mmkjflb.mmloan.model.entity.SalariesEntity;
import com.mmkjflb.mmloan.model.entity.SocialInfoEntity;
import com.mmkjflb.mmloan.model.entity.SpecialMenuEntity;
import com.mmkjflb.mmloan.model.entity.StringEntity;
import com.mmkjflb.mmloan.model.entity.StudentsEntity;
import com.mmkjflb.mmloan.model.entity.SurelyEntity;
import com.mmkjflb.mmloan.model.entity.UpVersionBean;
import com.mmkjflb.mmloan.model.entity.UpdateAgreeNoEntity;
import com.mmkjflb.mmloan.model.entity.YiBaoBindCardEntity;
import com.mmkjflb.mmloan.model.entity.ZhiMaAuthEntity;
import com.mmkjflb.mmloan.model.entity.ZhiMaResultEntity;
import com.mmkjflb.mmloan.model.http.HttpHelper;
import com.mmkjflb.mmloan.model.prefs.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

@Singleton
public class DataManager implements HttpHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    private PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean isFirstCome() {
        return mPreferencesHelper.isFirstCome();
    }

    @Override
    public void setFirstCome(boolean state) {
        mPreferencesHelper.setFirstCome(state);
    }

    @Override
    public Flowable<BaseBean<ChannelGoogleEntity>> insertChannelGoogle(Map<String, Object> map) {
        return mHttpHelper.insertChannelGoogle(map);
    }

    @Override
    public Flowable<BaseBean<AmountTypeEntity>> getAmountType(String sessionId) {
        return mHttpHelper.getAmountType(sessionId);
    }

    @Override
    public Flowable<BaseBean> smsInfos(String sessionId, RequestBody requestBody) {
        return mHttpHelper.smsInfos(sessionId, requestBody);
    }

    @Override
    public Flowable<BaseBean> updateProductCode(String sessionId, int productType) {
        return mHttpHelper.updateProductCode(sessionId, productType);
    }

    @Override
    public Flowable<BaseBean> afterBorrowCheck(Map<String, Object> map) {
        return mHttpHelper.afterBorrowCheck(map);
    }

    @Override
    public Flowable<BaseBean<String>> getQiniuToken(String sessionId) {
        return mHttpHelper.getQiniuToken(sessionId);
    }

    @Override
    public Flowable<BaseBean> confirmIncreaseCashDeposit(String productCode,String sessionId) {
        return mHttpHelper.confirmIncreaseCashDeposit(productCode,sessionId);
    }

    @Override
    public Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(String sessionId) {
        return mHttpHelper.getDepositInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean> saveOcrScore(Map<String, Object> map) {
        return mHttpHelper.saveOcrScore(map);
    }

    @Override
    public Flowable<BaseBean<IdCardCheckEntity>> idCardCheck(Map<String, RequestBody> partMap, MultipartBody.Part multipartPart) {
        return mHttpHelper.idCardCheck(partMap, multipartPart);
    }

    @Override
    public Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(String sessionId) {
        return mHttpHelper.getWithdrawalNumber(sessionId);
    }

    @Override
    public Flowable<BaseBean<Map<String, Object>>> getWithdrawChannel() {
        return mHttpHelper.getWithdrawChannel();
    }

    @Override
    public Flowable<BaseBean> renewalMobilePhone(Map<String, Object> map) {
        return mHttpHelper.renewalMobilePhone(map);
    }

    @Override
    public Flowable<BaseBean> checkUserInfo(Map<String, Object> map) {
        return mHttpHelper.checkUserInfo(map);
    }

    @Override
    public Flowable<BaseBean<IsPaidEntity>> isPaid(String sessionId) {
        return mHttpHelper.isPaid(sessionId);
    }

    @Override
    public Flowable<BaseBean<CollectionAccountEntity>> collectionAccount(String sessionId) {
        return mHttpHelper.collectionAccount(sessionId);
    }

    @Override
    public Flowable<BaseBean<SpecialMenuEntity>> specialMenu(String sessionId) {
        return mHttpHelper.specialMenu(sessionId);
    }

    @Override
    public Flowable<BaseBean<ApplyForPromoteEntity>> applyForPromote(String sessionId) {
        return mHttpHelper.applyForPromote(sessionId);
    }

    @Override
    public Flowable<BaseBean> submintDevice(RequestBody requestBody) {
        return mHttpHelper.submintDevice(requestBody);
    }

    @Override
    public Flowable<BaseBean<QueryProductInfoEntity>> queryProductInfo() {
        return mHttpHelper.queryProductInfo();
    }

    @Override
    public Flowable<BaseBean<CertificateDetailEntity>> certificateDetail(String sessionId, int bidId, int billId) {
        return mHttpHelper.certificateDetail(sessionId, bidId, billId);
    }

    @Override
    public Flowable<BaseBean<QueryRefundInfoEntity>> queryRefundInfo(String sessionId) {
        return mHttpHelper.queryRefundInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean> affirmRefund(Map<String, Object> map) {
        return mHttpHelper.affirmRefund(map);
    }

    @Override
    public Flowable<BaseBean> generateCashDepositVaOrOtc(Map<String, Object> map) {
        return mHttpHelper.generateCashDepositVaOrOtc(map);
    }

    @Override
    public Flowable<BaseBean> generateAuditDepositVaOrOtc(Map<String, Object> map) {
        return mHttpHelper.generateAuditDepositVaOrOtc(map);
    }

    @Override
    public Flowable<BaseBean<PaymentForFasPayEntity>> paymentForFasPay(Map<String, Object> map) {
        return mHttpHelper.paymentForFasPay(map);
    }

    @Override
    public Flowable<BaseBean<List<QueryPaymentChannelEntity>>> queryPaymentChannel(String sessionId) {
        return mHttpHelper.queryPaymentChannel(sessionId);
    }

    @Override
    public Flowable<AdviseEntity> getGoogleAdvise(Map<String, Object> options) {
        return mHttpHelper.getGoogleAdvise(options);
    }

    @Override
    public Flowable<BaseBean<BankInfoEntity>> getBankInfo(String sessionId) {
        return mHttpHelper.getBankInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<AssertsInfoEntity>> getAssertsInfo(String sessionId) {
        return mHttpHelper.getAssertsInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<SocialInfoEntity>> getSocialInfo(String sessionId) {
        return mHttpHelper.getSocialInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<BaseInfoEntity>> getBaseInfo(String sessionId) {
        return mHttpHelper.getBaseInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<NoticeEntity>> getOperatingNotice(String noticeCode) {
        return mHttpHelper.getOperatingNotice(noticeCode);

    }

    @Override
    public Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService() {
        return mHttpHelper.getCustomerService();
    }

    @Override
    public Flowable<BaseBean<ExtraCreditBean>> getExtraCredit(String sessionId) {
        return mHttpHelper.getExtraCredit(sessionId);
    }

    @Override
    public Flowable<BaseBean> submitReportToken(String sessionId, String reportTaskToken, String website) {
        return mHttpHelper.submitReportToken(sessionId, reportTaskToken, website);
    }

    @Override
    public Flowable<BaseBean> submitCallRecord(String sessionId, String records) {
        return mHttpHelper.submitCallRecord(sessionId, records);
    }

    @Override
    public Flowable<BaseBean<GCashDetailBean>> getGCashDetail(String sessionId) {
        return mHttpHelper.getGCashDetail(sessionId);
    }

    @Override
    public Flowable<BaseBean> updateGCash(String sessionId, String userGCashAccount, String captcha) {
        return mHttpHelper.updateGCash(sessionId, userGCashAccount, captcha);
    }

    @Override
    public Flowable<BaseBean> sendSms(String mobile, String smsCode) {
        return mHttpHelper.sendSms(mobile, smsCode);
    }

    @Override
    public Flowable<ResponseBody> getVerifycode(String mobile) {
        return mHttpHelper.getVerifycode(mobile);
    }

    @Override
    public Flowable<BaseBean> register(Map<String, Object> map) {
        return mHttpHelper.register(map);
    }

    @Override
    public Flowable<BaseBean<List<BankListEntity>>> bankList(String sessionId) {
        return mHttpHelper.bankList(sessionId);
    }

    @Override
    public Flowable<BaseBean> bankinfo(Map<String, Object> params) {
        return mHttpHelper.bankinfo(params);
    }

    @Override
    public Flowable<BaseBean> idcheck(Map<String, Object> partMap) {
        return mHttpHelper.idcheck(partMap);
    }


    @Override
    public Flowable<BaseBean> baseinfo(Map<String, Object> map) {
        return mHttpHelper.baseinfo(map);

    }

    @Override
    public Flowable<BaseBean> certificateUpload(Map<String, Object> map) {
        return mHttpHelper.certificateUpload(map);
    }


    @Override
    public Flowable<BaseBean> socialinfo(Map<String, Object> map) {
        return mHttpHelper.socialinfo(map);
    }

    @Override
    public Flowable<BaseBean> assertsinfo(Map<String, Object> map) {
        return mHttpHelper.assertsinfo(map);
    }

    @Override
    public Flowable<BaseBean<List<IslandProvinceCityEntity>>> getIslandsInfo(String sessionId) {
        return mHttpHelper.getIslandsInfo(sessionId);

    }

    @Override
    public Flowable<BaseBean<LoginBean>> login(String mobile, String password) {
        return mHttpHelper.login(mobile, password);
    }

    @Override
    public Flowable<BaseBean> forgotPwd(Map<String, String> map) {
        return mHttpHelper.forgotPwd(map);

    }

    @Override
    public Flowable<BaseBean> applyLoan(Map<String, Object> map) {
        return mHttpHelper.applyLoan(map);
    }

    @Override
    public Flowable<BaseBean> contacts(String sessionId, String contactsList, String firstContactMobile, String firstContactName, String secondContactMobile, String secondContactName) {
        return mHttpHelper.contacts(sessionId, contactsList, firstContactMobile, firstContactName, secondContactMobile, secondContactName);
    }

    @Override
    public Flowable<BaseBean> phoneCaptcha(String sessionId, String mobile, String moblieServicePassword, String captcha) {
        return mHttpHelper.phoneCaptcha(sessionId, mobile, moblieServicePassword, captcha);
    }

    @Override
    public Flowable<BaseBean<StringEntity>> initcompare(String sessionId, String name, String idNumber, String phoneNumber) {
        return mHttpHelper.initcompare(sessionId, name, idNumber, phoneNumber);
    }

    @Override
    public Flowable<BaseBean<ZhiMaResultEntity>> queryZhiMaResult(String sessionId, String name, String idNumber, String phoneNumber) {
        return mHttpHelper.queryZhiMaResult(sessionId, name, idNumber, phoneNumber);
    }

    @Override
    public Flowable<BaseBean<AuthentStatusEntity>> authentStatus(String sessionId) {
        return mHttpHelper.authentStatus(sessionId);
    }

    @Override
    public Flowable<BaseBean> batchUpload(RequestBody sessionId, MultipartBody.Part front, MultipartBody.Part back) {
        return mHttpHelper.batchUpload(sessionId, front, back);
    }

    @Override
    public Flowable<BaseBean<OcrCheckEntity>> userOcrCheck(String sessionId) {
        return mHttpHelper.userOcrCheck(sessionId);
    }

    @Override
    public Flowable<BaseBean<BooleanEntity>> checkRealName(String sessionId, String name, String idNumber) {
        return mHttpHelper.checkRealName(sessionId, name, idNumber);
    }

    @Override
    public Flowable<BaseBean> userBaseInfo(Map<String, RequestBody> partMap, MultipartBody.Part photo) {
        return mHttpHelper.userBaseInfo(partMap, photo);
    }

    @Override
    public Flowable<BaseBean<StringEntity>> auth(String sessionId, String type, String name, String idNumber, String mobileNo) {
        return mHttpHelper.auth(sessionId, type, name, idNumber, mobileNo);
    }

    @Override
    public Flowable<BaseBean<ZhiMaAuthEntity>> queryAuth(String sessionId, String name, String idNumber, String phoneNumber) {
        return mHttpHelper.queryAuth(sessionId, name, idNumber, phoneNumber);
    }

    @Override
    public Flowable<BaseBean> line(String sessionId) {
        return mHttpHelper.line(sessionId);
    }

    @Override
    public Flowable<BaseBean> deviceInfo(Map<String, String> map) {
        return mHttpHelper.deviceInfo(map);
    }

    @Override
    public Flowable<BaseBean<ArtificialAuthentStatusEntity>> queryCredit(String sessionId) {
        return mHttpHelper.queryCredit(sessionId);
    }

    @Override
    public Flowable<BaseBean<List<ProductInfoBean>>> getProductInfo(String sessionId, int type) {
        return mHttpHelper.getProductInfo(sessionId, type);
    }

    @Override
    public Flowable<BaseBean<BindCardEntity>> bindingBank(Map<String, String> map) {
        return mHttpHelper.bindingBank(map);
    }

    @Override
    public Flowable<BaseBean<CardManagerEntity>> queryUserBankCard(String sessionId) {
        return mHttpHelper.queryUserBankCard(sessionId);
    }

    @Override
    public Flowable<BaseBean<RechargeEntity>> submitRecharge(Map<String, String> map) {
        return mHttpHelper.submitRecharge(map);
    }

    @Override
    public Flowable<BaseBean<MainEntity>> balance(String sessionId) {
        return mHttpHelper.balance(sessionId);
    }

    @Override
    public Flowable<BaseBean<List<LoanEntity>>> loanInfo(String sessionId) {
        return mHttpHelper.loanInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<List<PaymentShopListEntity>>> getPaymentShopListAll() {
        return mHttpHelper.getPaymentShopListAll();
    }

    @Override
    public Flowable<BaseBean<SurelyEntity>> surely(Map<String, Object> params) {
        return mHttpHelper.surely(params);
    }

    @Override
    public Flowable<BaseBean> applySuggest(Map<String, String> params) {
        return mHttpHelper.applySuggest(params);
    }

    @Override
    public Flowable<BaseBean> updatePwd(Map<String, String> params) {
        return mHttpHelper.updatePwd(params);
    }


    @Override
    public Flowable<BaseBean<List<RecordEntity>>> loanRepaymentRecord(String sessionId) {
        return mHttpHelper.loanRepaymentRecord(sessionId);
    }

    @Override
    public Flowable<BaseBean<RepayEntity>> queryBillsInfo(String sessionId) {
        return mHttpHelper.queryBillsInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<List<Integer>>> getRepaymentShop() {
        return mHttpHelper.getRepaymentShop();
    }

    @Override
    public Flowable<BaseBean<RepaymentShopEntity>> getRepaymentShopNew(String sessionId) {
        return mHttpHelper.getRepaymentShopNew(sessionId);
    }

    @Override
    public Flowable<BaseBean<BankTypeEntity>> getPayTypeAndBankType(String sessionId) {
        return mHttpHelper.getPayTypeAndBankType(sessionId);
    }

    @Override
    public Flowable<BaseBean> submitActiveRepay(Map<String, Object> params) {
        return mHttpHelper.submitActiveRepay(params);
    }

    @Override
    public Flowable<BaseBean> updateRepayChannel(Map<String, Object> params) {
        return mHttpHelper.updateRepayChannel(params);
    }

    @Override
    public Flowable<BaseBean<PaymentData>> getPaymentCode(String sessionId) {
        return mHttpHelper.getPaymentCode(sessionId);
    }

    @Override
    public Flowable<BaseBean<AuthEntity>> submitAuth(Map<String, String> params) {
        return mHttpHelper.submitAuth(params);
    }

    @Override
    public Flowable<BaseBean<UpVersionBean>> getAppVersion(String platformType, String packageType) {
        return mHttpHelper.getAppVersion(platformType, packageType);
    }

    @Override
    public Flowable<BaseBean<GuideEntity>> getAppActivity(Map<String, String> params) {
        return mHttpHelper.getAppActivity(params);
    }

    @Override
    public Flowable<ResponseBody> updateUrl(String fileUrl) {
        return mHttpHelper.updateUrl(fileUrl);
    }

    @Override
    public Flowable<BaseBean<UpdateAgreeNoEntity>> updateCardAgreeNo(Map<String, String> params) {
        return mHttpHelper.updateCardAgreeNo(params);
    }

    @Override
    public Flowable<BaseBean<PreventionEntity>> prevention(String sessionId) {
        return mHttpHelper.prevention(sessionId);
    }

    @Override
    public Flowable<BaseBean<ReauthenticationEntity>> reauthentication(String sessionId) {
        return mHttpHelper.reauthentication(sessionId);
    }

    @Override
    public Flowable<BaseBean> saveExtendPeriod(String sessionId, int extendPeriod, String token) {
        return mHttpHelper.saveExtendPeriod(sessionId, extendPeriod, token);
    }

    @Override
    public Flowable<BaseBean<List<QueryActivityEntity>>> queryActivity(String sessionId) {
        return mHttpHelper.queryActivity(sessionId);
    }

    @Override
    public Flowable<BaseBean<QueryInviteActivityEntity>> queryInviteActivity(String sessionId) {
        return mHttpHelper.queryInviteActivity(sessionId);
    }

    @Override
    public Flowable<BaseBean<ArrayList<CouponEntity>>> querycoupon(String sessionId) {
        return mHttpHelper.querycoupon(sessionId);
    }

    @Override
    public Flowable<BaseBean<ArrayList<PositionEntity>>> queryPosition(String sessionId, long typeId) {
        return mHttpHelper.queryPosition(sessionId, typeId);
    }

    @Override
    public Flowable<BaseBean<PreventionEntity>> preventionForExtend(String sessionId) {
        return mHttpHelper.preventionForExtend(sessionId);
    }

    @Override
    public Flowable<BaseBean> payConfirmSms(Map<String, Object> params) {
        return mHttpHelper.payConfirmSms(params);
    }

    @Override
    public Flowable<BaseBean> paySmSResend(Map<String, Object> params) {
        return mHttpHelper.paySmSResend(params);
    }

    @Override
    public Flowable<BaseBean> bindCardSmSconfirm(Map<String, Object> params) {
        return mHttpHelper.bindCardSmSconfirm(params);
    }

    @Override
    public Flowable<BaseBean> bindCardSmSResend(Map<String, Object> params) {
        return mHttpHelper.bindCardSmSResend(params);
    }

    @Override
    public Flowable<BaseBean<YiBaoBindCardEntity>> yiBaoBindCard(Map<String, Object> params) {
        return mHttpHelper.yiBaoBindCard(params);
    }

    @Override
    public Flowable<BaseBean> livingidentifysave(Map<String, Object> params) {
        return mHttpHelper.livingidentifysave(params);
    }

    @Override
    public Flowable<BaseBean> zmScoreSave(Map<String, Object> params) {
        return mHttpHelper.zmScoreSave(params);
    }

    @Override
    public Flowable<BaseBean> getOperatingNotice() {
        return mHttpHelper.getOperatingNotice();
    }

    @Override
    public Flowable<BaseBean<MyBorrowBean>> loanRecord(String sessionId) {
        return mHttpHelper.loanRecord(sessionId);
    }

    @Override
    public Flowable<BaseBean> freelanceinfo(Map<String, Object> params) {
        return mHttpHelper.freelanceinfo(params);
    }

    @Override
    public Flowable<BaseBean> salariesinfo(Map<String, Object> params) {
        return mHttpHelper.salariesinfo(params);
    }

    @Override
    public Flowable<BaseBean<List<CompanyNatureEntity>>> getCompanyNature() {
        return mHttpHelper.getCompanyNature();
    }


    @Override
    public Flowable<BaseBean> selfemployinfo(Map<String, RequestBody> params, MultipartBody.Part businessLicense) {
        return mHttpHelper.selfemployinfo(params, businessLicense);
    }

    @Override
    public Flowable<BaseBean> studentinfo(Map<String, RequestBody> params, MultipartBody.Part studentCard) {
        return mHttpHelper.studentinfo(params, studentCard);
    }

    @Override
    public Flowable<BaseBean<ContactsEntity>> getContactsInfo(String sessionId) {
        return mHttpHelper.getContactsInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<SalariesEntity>> getSalariesInfo(String sessionId) {
        return mHttpHelper.getSalariesInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<PrivateOwnerEntity>> getSelfemployInfo(String sessionId) {
        return mHttpHelper.getSelfemployInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<FreelanceEntity>> getFreelanceInfo(String sessionId) {
        return mHttpHelper.getFreelanceInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<StudentsEntity>> getStudentInfo(String sessionId) {
        return mHttpHelper.getStudentInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean> rollbackStatus(String sessionId) {
        return mHttpHelper.rollbackStatus(sessionId);
    }

    @Override
    public Flowable<BaseBean<AuditDepositEntity>> queryAuditDepositRefundInfo(String sessionId) {
        return mHttpHelper.queryAuditDepositRefundInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<RefundDepositEntity>> refundDeposit(String sessionId, String channel) {
        return mHttpHelper.refundDeposit(sessionId, channel);
    }

    @Override
    public Flowable<BaseBean<RefundInfoEntity>> getRefundInfo(String sessionId) {
        return mHttpHelper.getRefundInfo(sessionId);
    }

    @Override
    public Flowable<BaseBean<RefundCodeEntity>> getRefundCode(String sessionId) {
        return mHttpHelper.getRefundCode(sessionId);
    }

    @Override
    public Flowable<BaseBean> auditDepositAffirmRefund(String sessionId) {
        return mHttpHelper.auditDepositAffirmRefund(sessionId);
    }

    @Override
    public Flowable<BaseBean<DepositCodeEntity>> getDepositCode(RequestBody sessionId) {
        return mHttpHelper.getDepositCode(sessionId);
    }

    @Override
    public Flowable<BaseBean<DepositCodeEntity>> queryDepositCode(String sessionId) {
        return mHttpHelper.queryDepositCode(sessionId);
    }
}

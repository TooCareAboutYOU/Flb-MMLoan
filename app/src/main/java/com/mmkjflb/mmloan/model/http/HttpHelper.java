package com.mmkjflb.mmloan.model.http;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface HttpHelper {
    Flowable<BaseBean<ChannelGoogleEntity>> insertChannelGoogle(Map<String, Object> map);
    Flowable<BaseBean<AmountTypeEntity>> getAmountType(String sessionId);
    Flowable<BaseBean> smsInfos(String sessionId, RequestBody requestBody);
    Flowable<BaseBean> updateProductCode(String sessionId, int productType);

    Flowable<BaseBean> afterBorrowCheck(Map<String, Object> map);

    Flowable<BaseBean<String>> getQiniuToken(String sessionId);

    Flowable<BaseBean> confirmIncreaseCashDeposit(String productCode,String sessionId);

    Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(String sessionId);

    Flowable<BaseBean> saveOcrScore(Map<String, Object> map);

    Flowable<BaseBean<IdCardCheckEntity>> idCardCheck(Map<String, RequestBody> partMap, MultipartBody.Part multipartPart);

    Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(String sessionId);

    Flowable<BaseBean<Map<String, Object>>> getWithdrawChannel();

    Flowable<BaseBean> renewalMobilePhone(Map<String, Object> map);

    Flowable<BaseBean> checkUserInfo(Map<String, Object> map);

    Flowable<BaseBean<IsPaidEntity>> isPaid(String sessionId);

    Flowable<BaseBean<CollectionAccountEntity>> collectionAccount(String sessionId);

    Flowable<BaseBean<SpecialMenuEntity>> specialMenu(String sessionId);

    Flowable<BaseBean<ApplyForPromoteEntity>> applyForPromote(String sessionId);

    Flowable<BaseBean> submintDevice(RequestBody requestBody);

    Flowable<BaseBean<QueryProductInfoEntity>> queryProductInfo();

    Flowable<BaseBean<CertificateDetailEntity>> certificateDetail(String sessionId, int bidId, int billId);

    Flowable<BaseBean<QueryRefundInfoEntity>> queryRefundInfo(String sessionId);

    Flowable<BaseBean> affirmRefund(Map<String, Object> map);

    Flowable<BaseBean> generateCashDepositVaOrOtc(Map<String, Object> map);

    Flowable<BaseBean> generateAuditDepositVaOrOtc(Map<String, Object> map);

    Flowable<BaseBean<PaymentForFasPayEntity>> paymentForFasPay(Map<String, Object> map);

    Flowable<BaseBean<List<QueryPaymentChannelEntity>>> queryPaymentChannel(String sessionId);

    Flowable<AdviseEntity> getGoogleAdvise(Map<String, Object> options);

    Flowable<BaseBean<BankInfoEntity>> getBankInfo(String sessionId);

    Flowable<BaseBean<AssertsInfoEntity>> getAssertsInfo(String sessionId);

    Flowable<BaseBean<SocialInfoEntity>> getSocialInfo(String sessionId);

    Flowable<BaseBean<BaseInfoEntity>> getBaseInfo(String sessionId);

    Flowable<BaseBean<NoticeEntity>> getOperatingNotice(String noticeCode);

    Flowable<BaseBean> sendSms(String mobile, String smsCode);

    Flowable<ResponseBody> getVerifycode(String mobile);

    Flowable<BaseBean> register(Map<String, Object> map);

    Flowable<BaseBean<List<BankListEntity>>> bankList(String sessionId);

    Flowable<BaseBean> bankinfo(Map<String, Object> params);

    Flowable<BaseBean> idcheck(Map<String, Object> partMap);

    Flowable<BaseBean> baseinfo(Map<String, Object> map);

    Flowable<BaseBean> certificateUpload(Map<String, Object> map);

    Flowable<BaseBean> socialinfo(Map<String, Object> map);

    Flowable<BaseBean> assertsinfo(Map<String, Object> map);

    Flowable<BaseBean<List<IslandProvinceCityEntity>>> getIslandsInfo(String sessionId);

    Flowable<BaseBean<LoginBean>> login(String mobile, String password);

    Flowable<BaseBean> forgotPwd(Map<String, String> map);

    Flowable<BaseBean> applyLoan(Map<String, Object> map);

    Flowable<BaseBean> contacts(String sessionId, String contactsList, String firstContactMobile, String firstContactName, String secondContactMobile, String secondContactName);

    Flowable<BaseBean> phoneCaptcha(String sessionId, String mobile, String moblieServicePassword, String captcha);

    Flowable<BaseBean<StringEntity>> initcompare(String sessionId, String name,
                                                 String idNumber, String phoneNumber);

    Flowable<BaseBean<ZhiMaResultEntity>> queryZhiMaResult(String sessionId, String name,
                                                           String idNumber, String phoneNumber);

    Flowable<BaseBean<AuthentStatusEntity>> authentStatus(String sessionId);

    Flowable<BaseBean> batchUpload(RequestBody sessionId, MultipartBody.Part front, MultipartBody.Part back);

    Flowable<BaseBean<OcrCheckEntity>> userOcrCheck(String sessionId);

    Flowable<BaseBean<BooleanEntity>> checkRealName(String sessionId, String name, String idNumber);

    Flowable<BaseBean> userBaseInfo(Map<String, RequestBody> partMap, MultipartBody.Part photo);

    Flowable<BaseBean<StringEntity>> auth(String sessionId, String type, String name, String idNumber, String mobileNo);

    Flowable<BaseBean<ZhiMaAuthEntity>> queryAuth(String sessionId, String name, String idNumber, String phoneNumber);

    Flowable<BaseBean> line(String sessionId);

    Flowable<BaseBean> deviceInfo(Map<String, String> map);

    Flowable<BaseBean<ArtificialAuthentStatusEntity>> queryCredit(String sessionId);

    Flowable<BaseBean<List<ProductInfoBean>>> getProductInfo(String sessionId, int type);


    Flowable<BaseBean<BindCardEntity>> bindingBank(Map<String, String> map);

    Flowable<BaseBean<CardManagerEntity>> queryUserBankCard(String sessionId);

    Flowable<BaseBean<RechargeEntity>> submitRecharge(Map<String, String> map);

    Flowable<BaseBean<MainEntity>> balance(String sessionId);

    Flowable<BaseBean<List<LoanEntity>>> loanInfo(String sessionId);

    Flowable<BaseBean<List<PaymentShopListEntity>>> getPaymentShopListAll();

    Flowable<BaseBean<SurelyEntity>> surely(Map<String, Object> params);

    Flowable<BaseBean> applySuggest(Map<String, String> params);

    Flowable<BaseBean> updatePwd(Map<String, String> params);

    Flowable<BaseBean<List<RecordEntity>>> loanRepaymentRecord(String sessionId);

    Flowable<BaseBean<RepayEntity>> queryBillsInfo(String sessionId);

    Flowable<BaseBean<List<Integer>>> getRepaymentShop();

    Flowable<BaseBean<RepaymentShopEntity>> getRepaymentShopNew(String sessionId);

    Flowable<BaseBean<BankTypeEntity>> getPayTypeAndBankType(String sessionId);

    Flowable<BaseBean> submitActiveRepay(Map<String, Object> params);

    Flowable<BaseBean> updateRepayChannel(Map<String, Object> params);

    Flowable<BaseBean<PaymentData>> getPaymentCode(String sessionId);

    Flowable<BaseBean<AuthEntity>> submitAuth(Map<String, String> params);

    Flowable<BaseBean<UpVersionBean>> getAppVersion(String platformType, String packageType);

    Flowable<BaseBean<GuideEntity>> getAppActivity(Map<String, String> params);

    Flowable<ResponseBody> updateUrl(String fileUrl);

    Flowable<BaseBean<UpdateAgreeNoEntity>> updateCardAgreeNo(Map<String, String> params);

    Flowable<BaseBean<PreventionEntity>> prevention(String sessionId);

    Flowable<BaseBean<ReauthenticationEntity>> reauthentication(String sessionId);

    Flowable<BaseBean> saveExtendPeriod(String sessionId, int extendPeriod, String token);

    Flowable<BaseBean<List<QueryActivityEntity>>> queryActivity(String sessionId);

    Flowable<BaseBean<QueryInviteActivityEntity>> queryInviteActivity(String sessionId);

    Flowable<BaseBean<ArrayList<CouponEntity>>> querycoupon(String sessionId);

    Flowable<BaseBean<ArrayList<PositionEntity>>> queryPosition(String sessionId, long typeId);

    Flowable<BaseBean<PreventionEntity>> preventionForExtend(String sessionId);

    Flowable<BaseBean> payConfirmSms(Map<String, Object> params);

    Flowable<BaseBean> paySmSResend(Map<String, Object> params);

    Flowable<BaseBean> bindCardSmSconfirm(Map<String, Object> params);

    Flowable<BaseBean> bindCardSmSResend(Map<String, Object> params);

    Flowable<BaseBean<YiBaoBindCardEntity>> yiBaoBindCard(Map<String, Object> params);


    Flowable<BaseBean> livingidentifysave(Map<String, Object> params);

    Flowable<BaseBean> zmScoreSave(Map<String, Object> params);

    Flowable<BaseBean> getOperatingNotice();

    Flowable<BaseBean<MyBorrowBean>> loanRecord(String sessionId);

    Flowable<BaseBean> freelanceinfo(Map<String, Object> params);

    Flowable<BaseBean> salariesinfo(Map<String, Object> params);

    Flowable<BaseBean<List<CompanyNatureEntity>>> getCompanyNature();

    Flowable<BaseBean> selfemployinfo(Map<String, RequestBody> params, MultipartBody.Part businessLicense);

    Flowable<BaseBean> studentinfo(Map<String, RequestBody> params, MultipartBody.Part studentCard);

    Flowable<BaseBean<ContactsEntity>> getContactsInfo(String sessionId);

    Flowable<BaseBean<SalariesEntity>> getSalariesInfo(String sessionId);

    Flowable<BaseBean<PrivateOwnerEntity>> getSelfemployInfo(String sessionId);

    Flowable<BaseBean<FreelanceEntity>> getFreelanceInfo(String sessionId);

    Flowable<BaseBean<StudentsEntity>> getStudentInfo(String sessionId);

    Flowable<BaseBean> rollbackStatus(String sessionId);

    Flowable<BaseBean<AuditDepositEntity>> queryAuditDepositRefundInfo(@Field("sessionId") String sessionId);

//	Flowable<BaseBean<CreditManagerEntity>> queryCreditManager(@Field("sessionId") String sessionId);

    Flowable<BaseBean<RefundInfoEntity>> getRefundInfo(@Field("sessionId") String sessionId);

    Flowable<BaseBean<RefundCodeEntity>> getRefundCode(@Field("sessionId") String sessionId);

    Flowable<BaseBean<RefundDepositEntity>> refundDeposit(@Field("sessionId") String sessionId, @Field("withdrawChannel") String withdrawChannel);

    Flowable<BaseBean> auditDepositAffirmRefund(@Field("sessionId") String sessionId);

    Flowable<BaseBean<DepositCodeEntity>> getDepositCode(RequestBody sessionId);

    Flowable<BaseBean<DepositCodeEntity>> queryDepositCode(String sessionId);

    Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService();

    Flowable<BaseBean<ExtraCreditBean>> getExtraCredit(String sessionId);

    Flowable<BaseBean> submitReportToken(String sessionId, String reportTaskToken, String website);

    Flowable<BaseBean> submitCallRecord(String sessionId, String records);

    Flowable<BaseBean<GCashDetailBean>> getGCashDetail(String sessionId);

    Flowable<BaseBean> updateGCash(String sessionId, String userGCashAccount, String captcha);


}

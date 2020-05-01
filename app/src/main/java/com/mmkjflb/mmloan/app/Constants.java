package com.mmkjflb.mmloan.app;


import com.mmkj.lib.flb.BaseConfig;

import java.io.File;


public class Constants {
	public static final String IDCARD_NAME = "file";//身份证识别字段
	public static final String VERSION_TYPE = "android";//android版本更新
	public static final String DEVICE_TYPE = "Android";//提交设备信息
	public static final String LOGIN_BOLD_STR1 = "No Account? ";//登录变粗字体1
	public static final String LOGIN_BOLD_STR2 = "Sign Up";//登录变粗字体2
	public static final String MD5_KEY = "mmfq";

	public static final long SPLASH_TIMECOUNT = 3;

	public static final String PATH_DATA = BaseApplication.getInstance().getFilesDir().getAbsolutePath() + File.separator + "data";

	public static final String PATH_CACHE = PATH_DATA + "/DhfCache";

	public static final long TIMECOUNT = 120;  //验证码倒计时
	public static boolean STUDENTSELECTIMAGE = false;
	public static boolean PAYROLLSELECTIMAGE = false;
	public static boolean PRIVATEOWNERSELECTIMAGE = false;
	public static boolean PAYROLLETLISTENONE = false;
	public static boolean PAYROLLETLISTENTWO = false;
	public static boolean PRIVATEOWNERETLISTENONE = false;
	public static boolean STUDENTETLISTENONE = false;
	public static boolean PAY_DATE_STATES=false;

	public static boolean SUBMIT_STATUS=false;
	public static final String PRIVATE_AGREEMENT = BaseConfig.getInstance().getBaseUrl()+ "protocol/rightagreement_mm.html";
	public static final String FAQ = BaseConfig.getInstance().getBaseUrl()+ "protocol/mmloan.html";
	public static final String SERVICE_AGREEMENT = BaseConfig.getInstance().getBaseUrl()+ "protocol/phzcxy_mm.html";



	public static final int CAREERCODE = 22;//职业 requestcode
}

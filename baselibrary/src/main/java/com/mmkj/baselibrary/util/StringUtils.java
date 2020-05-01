package com.mmkj.baselibrary.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;

/**
 * Created by Administrator on 2017/10/20.
 */

public class StringUtils {

    private static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|[\ue000-\uf8ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
    private static String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 截取从指定字符串索引值后的所有字符，不包含索引字符
     */
    public static String subStringToEndExincludeStart(@NonNull String str, @NonNull String key) {
        if (!TextUtils.isEmpty(str)) {
            return str.substring(str.lastIndexOf(key) + 1, str.length());
        }
        return null;
    }

    public static String filter(String str) {
//        str.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5\\s*]", "");
        return str.replaceAll("[^a-zA-Z0-9\\s*]", "");  //去除数字，英文，汉字  之外的内容
    }

    /**
     * 过滤表情<br>
     */
    public static String filterEmoji(String source) {
        if (!TextUtils.isEmpty(source)) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("");
                return source;
            }
            return source;
        }
        return source;
    }

    public static Map<String, String> decodeUrlParameters(String s) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        try {
            s = s != null ? s.trim() : null;
            if (s == null || s.isEmpty()) {
                return params;
            }
            String[] array = s.split("&");
            for (String parameter : array) {
                String[] kv = parameter.split("=");
                String k = kv.length > 0 ? kv[0] : null;
                String v = kv.length > 1 ? kv[1] : null;
                if (k == null || k.isEmpty()) {
                    continue;
                }
                String dk = URLDecoder.decode(k, "UTF-8");
                if (v != null) {
                    params.put(dk, URLDecoder.decode(v, "UTF-8"));
                } else {
                    params.put(dk, "");
                }
            }
            return params;
        } catch (Exception e) {
            e.printStackTrace();
            return params;
        }
    }


    public static boolean judgeEmail(String string) {
        if (string == null) {
            return false;
        }
        return string.contains("@");
    }

    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        return m.matches();
    }

    public static String cutAmount(double amount) {
        return cutOutLastThree(doubleZheng(amount));
    }

    public static String cutOutLastThree(String cutStr) {
        int index = cutStr.indexOf(".");
        if (index > -1) {
            StringBuilder str = new StringBuilder();
            String pre = cutStr.substring(0, index);
            String end = cutStr.substring(index);
            str.append(pre);
            // 从后往前每隔三位插入逗号
            int last = pre.length();
            for (int i = last - 3; i > 0; i -= 3) {
                str.insert(i, ',');
            }
            str.append(end);
            return str.toString();
        } else {
            StringBuilder str = new StringBuilder();
            str.append(cutStr);
            // 从后往前每隔三位插入逗号
            int last = str.length();
            for (int i = last - 3; i > 0; i -= 3) {
                str.insert(i, ',');
            }
            return str.toString();
        }
    }

    /**
     * 判断手机号码格式校验
     */
    private static Pattern p = Pattern.compile("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

    public static boolean isMobileNO(String mobiles) {
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    private static String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";

    public static boolean isSpecialChar(String str) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    /**
     * 限制8-11位数字和字母组合
     *
     * @param input
     * @return
     */
    private static Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,11}$");

    public static boolean checkInput(String input) {
        Matcher m = pattern.matcher(input);
        if (!m.matches()) { //匹配不到,說明輸入的不符合條件
            return false;
        }
        return true;
    }


//    /**
//     * 保留两位小数
//     */
//    public static String doubleZheng(double index) {
//        try {
//            DecimalFormat fmt = new DecimalFormat("0.00");
//            return fmt.format(index);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            return "0.00";
//        }
//    }
//
//    /**
//     * double小数点后为0取整否则保留
//     */
//    public static String doubleZheng(double index) {
//        try {
//            DecimalFormat fmt = new DecimalFormat("0.00");
//            return fmt.format(index);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            return "0.00";
//        }
//    }

    /**
     * 取整数
     */
    public static String doubleZheng(@NonNull double index) {
        try {
            DecimalFormat fmt = new DecimalFormat("0");
            return fmt.format(index);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "0";
        }
    }


    /**
     * @param string 字符串
     * @param before 前面显示个数
     * @param after  后面显示个数
     * @param count  中间隐藏个数
     * @return
     */
    public static String secureShow(@NonNull String string, int before, int after, int count) {
        String secureshow = "";
        String pointer = "";
        for (int i = 0; i < count; i++) {
            pointer += "*";
        }
        try {
            secureshow = string.substring(0, before) + pointer + string.substring(string.length() - after, string.length());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return secureshow;
    }

    public static String lastNumbShow(String str, int n) {
        String lastStr = "";
        try {
            lastStr = str.substring(str.length() - n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastStr;

    }

    /**
     * 以字母开头
     */
    public static boolean isStartWithAlphabet(String str) {
        String regEx = "^[a-zA-Z].*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    private static final int PLAY_STORE_MIN_APP_VER = 80837300;

    private static Integer sPlayStoreVersionCode = null;

    public static void checkInstallReferrer(final Context context) {
        if (sPlayStoreVersionCode == null) {
            sPlayStoreVersionCode = DeviceUtils.getVersionCode(context, "com.android.vending");
        }
        if (sPlayStoreVersionCode >= PLAY_STORE_MIN_APP_VER) {
            String installReferrer = PreferenceUtils.getString(PreferenceUtils.INSTALL_REFERRER, "");
            if (TextUtils.isEmpty(installReferrer)) {
                try {
                    final InstallReferrerClient referrerClient = InstallReferrerClient.newBuilder(context.getApplicationContext()).build();
                    Log.i("REFERRER", "startConnection");
                    referrerClient.startConnection(new InstallReferrerStateListener() {
                        @Override
                        public void onInstallReferrerSetupFinished(int responseCode) {
                            try {
                                switch (responseCode) {
                                    case InstallReferrerClient.InstallReferrerResponse.OK:
                                        ReferrerDetails referrerDetails = referrerClient.getInstallReferrer();
                                        if (referrerDetails != null) {
                                            PreferenceUtils.putString(PreferenceUtils.INSTALL_REFERRER, referrerDetails.getInstallReferrer());
                                            Log.i("REFERRER", "referrerDetails" + " installReferrer:" + referrerDetails.getInstallReferrer() + " referrerClickTimestampSeconds:" + referrerDetails.getReferrerClickTimestampSeconds() + " installBeginTimestampSeconds:" + referrerDetails.getInstallBeginTimestampSeconds());
                                        }
                                        referrerClient.endConnection();
                                        break;
                                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:

                                        Log.i("REFERRER", "onInstallReferrerSetupFinished FEATURE_NOT_SUPPORTED");
                                        break;
                                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                                        Log.i("REFERRER", "onInstallReferrerSetupFinished SERVICE_UNAVAILABLE");
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                Log.i("REFERRER", "getInstallReferrer", e);
                            }
                        }

                        @Override
                        public void onInstallReferrerServiceDisconnected() {
                        }
                    });
                } catch (Exception e) {
                    Log.i("REFERRER", "startConnection", e);
                }
            }
        }
    }

}

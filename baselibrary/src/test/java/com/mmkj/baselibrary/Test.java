package com.mmkj.baselibrary;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Optional;

public class Test {

    private static final String TAG = "MTest";

    static final String str = "123abc456";
    static final int i = 3;

    public static void main(String[] args) {
//        String result = str.substring(0, i);
//        System.out.println("取字符串的前" + i + "位：" + result);
//
//        String result1 = str.substring(i);
//        System.out.println("去掉字符串的前" + i + "位：" + result1);
//
//        String result2 = str.substring(str.length() - i);
//        System.out.println("从右边开始取" + i + "个字符：" + result2);
//
//        String result3 = str.substring(0, str.length() - i);
//        System.out.println("从右边开始去掉" + i + "个字符：" + result3);
//
//        StringBuffer stringBuilder1 = new StringBuffer("20180918");
//        stringBuilder1.insert(6, "-");
//        stringBuilder1.insert(4, "-");
//        System.out.println("打印：" + stringBuilder1.toString());
//
//        StringBuilder stringBuilder2 = new StringBuilder("1234abcdabc12");
//        int index = stringBuilder2.indexOf("abc");
//        stringBuilder2.insert(index, "131");
//        System.out.println("打印：" + stringBuilder2.toString());




        StringBuilder data = new StringBuilder("12345678909");
        try {
            if (Double.parseDouble(data.toString()) >= 0.00d || Integer.parseInt(data.toString()) >= 0 || Short.parseShort(data.toString()) == 0) {
                if (data.toString().contains(".")) {
                    int indexPoint = data.indexOf(".");
                    final String strs = data.substring(0, indexPoint);
                    data.delete(0, data.length());
                    data.append(strs);
                }
            }

        } catch (NumberFormatException nfe) {
            System.out.println("类型转换异常：" + nfe.getMessage());
            return;
        } finally {
            System.out.println("结束后续操作");
        }

        //方式一
//        int dataLength = data.length();
//        if (dataLength > 3 && dataLength <= 6) {
//            data.insert(dataLength - 3, ",");
//        } else if (dataLength > 6 && dataLength <= 9) {
//            data.insert(dataLength - 3, ",")
//                    .insert(dataLength - 6, ",");
//        } else if (dataLength > 9 && dataLength <= 12) {
//            data.insert(dataLength - 3, ",")
//                    .insert(dataLength - 6, ",")
//                    .insert(dataLength - 9, ",");
//        }else if (dataLength > 12 && dataLength <= 15) {
//            data.insert(dataLength - 3, ",")
//                    .insert(dataLength - 6, ",")
//                    .insert(dataLength - 9, ",")
//                    .insert(dataLength - 12, ",");
//        }
//        System.out.println("字符串插入,打印：" + data.toString());

        //方式二
//        double num=187123422.2;
        try {
            DecimalFormat fmt = new DecimalFormat(",###");
            fmt.setRoundingMode(RoundingMode.DOWN);
            System.out.println("打印："+fmt.format(Double.parseDouble(data.toString())));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

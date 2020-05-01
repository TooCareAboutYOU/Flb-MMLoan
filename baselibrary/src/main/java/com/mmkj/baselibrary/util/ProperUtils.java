package com.mmkj.baselibrary.util;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperUtils {

    /**
     配置：
         public static final String SERVE_URL_STR="SERVE_URL";
         public static String SERVE_URL="";

     获取：
         Properties properties= ProperUtils.getProperties(this.getApplicationContext(),"httpConfig.properties");
         Constants.SERVE_URL=properties.getProperty(Constants.SERVE_URL_STR);
         Log.i(TAG, "网络地址: "+Constants.SERVE_URL);
     */

    private static Properties urlProper;

    //httpConfig.properties
    public static Properties getProperties(Context context ,String fileName){
        Properties properties=new Properties();
        try {
            InputStream inputStream=context.getAssets().open(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlProper=properties;
        return urlProper;
    }


}

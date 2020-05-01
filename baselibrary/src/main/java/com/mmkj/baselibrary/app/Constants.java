package com.mmkj.baselibrary.app;


import java.io.File;

public class Constants {

	public static final String PATH_DATA = IBaseApplition.getiBaseInstance().getFilesDir().getAbsolutePath() + File.separator + "data";

	public static final String PATH_CACHE = PATH_DATA + "/DhfCache";

}

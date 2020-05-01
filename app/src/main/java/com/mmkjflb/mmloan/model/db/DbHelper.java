package com.mmkjflb.mmloan.model.db;

import com.mmkjflb.mmloan.model.entity.SplashEntity;
import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/9/29.
 */

public interface DbHelper {
	Observable<Boolean> saveQuestion(SplashEntity splashEntity);
}

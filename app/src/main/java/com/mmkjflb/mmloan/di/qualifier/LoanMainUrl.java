package com.mmkjflb.mmloan.di.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by codeest on 2017/2/26.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface LoanMainUrl {
}

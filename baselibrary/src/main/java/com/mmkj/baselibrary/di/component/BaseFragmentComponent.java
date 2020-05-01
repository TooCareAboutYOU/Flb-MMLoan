package com.mmkj.baselibrary.di.component;


import com.mmkj.baselibrary.di.module.BaseFragmentModule;
import com.mmkj.baselibrary.di.scope.FragmentScope;

import dagger.Component;


@FragmentScope
@Component(dependencies = BaseAppComponent.class, modules = BaseFragmentModule.class)
public interface BaseFragmentComponent {
	//	Activity getActivity();
//	void inject(PayrollFragment payrollFragment);
//
//	void inject(FreelanceFragment freelanceFragment);
//
//	void inject(PrivateOwnerFragment privateOwnerFragment);
//
//	void inject(StudentsFragment studentsFragment);
//
//	void inject(LoanFragment loanFragment);
//
//	void inject(UserCenterFragment userCenterFragment);
//
//	void inject(MaiMaiBorrowFragment maiMaiBorrowFragment);
//
//	void inject(CertificationMainFragment certificationMainFragment);
//
//	void inject(CertificationResultFragment certificationResultFragment);
}

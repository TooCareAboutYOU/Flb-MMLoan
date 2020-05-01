package com.mmkjflb.mmloan.di.component;

import com.mmkjflb.mmloan.di.module.FragmentModule;
import com.mmkjflb.mmloan.di.scope.FragmentScope;
import com.mmkjflb.mmloan.viewmodel.fragment.freelance.FreelanceFragment;
import com.mmkjflb.mmloan.viewmodel.fragment.payroll.PayrollFragment;
import com.mmkjflb.mmloan.viewmodel.fragment.privateowner.PrivateOwnerFragment;
import com.mmkjflb.mmloan.viewmodel.fragment.student.StudentsFragment;

import dagger.Component;


@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    //	Activity getActivity();
    void inject(PayrollFragment payrollFragment);

    void inject(FreelanceFragment freelanceFragment);

    void inject(PrivateOwnerFragment privateOwnerFragment);

    void inject(StudentsFragment studentsFragment);

}

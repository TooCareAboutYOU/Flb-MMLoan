package com.mmkjflb.mmloan.viewmodel.enploymentinformation;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityImageBinding;

import me.panpf.sketch.SketchImageView;
@Route(path = RouteConstant.IMAGE_PAGE)
@ActivityFragmentInject(contentViewId = R.layout.activity_image)
public class ImageActivity extends DataBindingActivity<ActivityImageBinding> {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image);
//        SketchImageView img=findViewById(R.id.acIv_img);
//        img.setZoomEnabled(true); //手势缩放
//        img.getZoomer().setZoomDuration(500);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        ConstraintLayout constraintLayout=findViewById(R.id.cl_container);
//        constraintLayout.setOnClickListener(v -> finish());
//
//    }



    @SuppressLint("CheckResult")
    @Override
    protected void initViews() {
        //手势缩放
        mDataBinding.acIvImg.setZoomEnabled(true);
        mDataBinding.acIvImg.getZoomer().setZoomDuration(500);

        RxViewUtil.clicks(mDataBinding.acIvImg).subscribe(o -> finish());

        mDataBinding.clContainer.setOnClickListener(v -> finish());
    }
}

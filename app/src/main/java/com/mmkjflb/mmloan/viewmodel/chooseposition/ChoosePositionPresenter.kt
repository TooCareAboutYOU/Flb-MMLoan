package com.mmkjflb.mmloan.viewmodel.chooseposition


import com.mmkjflb.mmloan.R
import com.mmkjflb.mmloan.model.DataManager
import com.mmkjflb.mmloan.model.entity.PositionEntity
import com.mmkj.baselibrary.base.RxPresenter
import com.mmkj.baselibrary.model.entitry.BaseBean
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject


/**
 * Created by wendjia
 * 2018\7\31 0031
 */

class ChoosePositionPresenter @Inject
constructor(private val mDataManager: DataManager) : RxPresenter<ChoosePositionContract.View>(), ChoosePositionContract.Presenter {

    var cache = mutableMapOf<Long, BaseBean<ArrayList<PositionEntity>>>()

    override fun queryPositionInfo(sessionId: String?, typeId: Long) {
        if (cache.containsKey(typeId)) {
            mView.queryPositionInfoSuc(cache[typeId])
        } else {
            addSubscribe(mDataManager.queryPosition(sessionId, typeId)
                    .doOnSubscribe { _ -> mView.showProgress() }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe({ baseBean ->
                        cache[typeId] = baseBean
                        mView.queryPositionInfoSuc(baseBean)
                        mView.hideProgress()
                    }, { _ -> mView.hideProgress(R.string.neterror_tryagain) }))
        }
    }


    override fun queryPosition(sessionId: String) {
        addSubscribe(mDataManager.queryPosition(sessionId, 0).doOnSubscribe { _ -> mView.showProgress() }.subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ baseBean ->
                    mView.queryPositionSuc(baseBean)
                    mView.hideProgress()
                }, { _ -> mView.hideProgress(R.string.neterror_tryagain) }))
    }

}

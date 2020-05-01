package com.mmkjflb.mmloan.model.entity

/**
 * Created by Administrator on 2017/10/29.
 */

class UpVersionBean {

    var id: Int = 0
    var isForceUpdate: Boolean = false // 强制更新
    var latestVersion: String? = null // 1.4.1
    var versioncode: Int = 0
    var downloadUrl: String? = null
    var updateDesc: String? = null
    override fun toString(): String {
        return "UpVersionBean(id=$id, isForceUpdate=$isForceUpdate, latestVersion=$latestVersion, versioncode=$versioncode, downloadUrl=$downloadUrl, updateDesc=$updateDesc)"
    }


}

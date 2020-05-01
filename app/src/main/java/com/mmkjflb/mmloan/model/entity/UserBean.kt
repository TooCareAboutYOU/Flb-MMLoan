package com.mmkjflb.mmloan.model.entity


class UserBean {


    /***
     * 申请金额界面
     * 四项认证页面
     * 获取授信分数未点击
     * 导流页
     * 等待页面
     * 金额页面
     */
    var mobilePhone: String? = null
    var userName: String? = null
    var idcard: String? = null
    var auditStep: Int = 0
    /**
     * id : 201806021554350158
     */
    var idCardType: String? = null

    var id: Long = 0

    override fun toString(): String {
        return "UserBean(mobilePhone=$mobilePhone, userName=$userName, idcard=$idcard, auditStep=$auditStep, idCardType=$idCardType, id=$id)"
    }


}

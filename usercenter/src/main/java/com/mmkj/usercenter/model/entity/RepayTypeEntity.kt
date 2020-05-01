package com.mmkj.usercenter.model.entity

class RepayTypeEntity {
    //    	Branch ID	Group	AccountHolder	Branch Name	Branch Address	City/Municipality	Province	Region	Business Type
    lateinit var platform: String
    lateinit var branchId: String
    lateinit var group: String
    lateinit var accountHolder: String
    lateinit var branchName: String
    lateinit var bracnchAddress: String
    lateinit var city: String
    lateinit var province: String
    lateinit var regino: String
    lateinit var businessType: String
    fun showBranchAddress(): String {
        return bracnchAddress.replace(":", ",")
    }

    override fun toString(): String {
        return "RepayTypeEntity(platform='$platform', branchId='$branchId', group='$group', accountHolder='$accountHolder', branchName='$branchName', bracnchAddress='$bracnchAddress', city='$city', province='$province', regino='$regino', businessType='$businessType')"
    }

}
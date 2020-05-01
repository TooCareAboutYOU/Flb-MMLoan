package com.mmkjflb.mmloan.model.entity
import com.google.gson.Gson
import java.math.BigDecimal



class SalariesEntity {

    /**
     * id : 107
     * userId : 201809051046310325
     * createTime : 1536144201000
     * updateTime : 1536199764000
     * companyName : Test
     * companyMobile : 18812341234
     * companyEmail : test@qq.com
     * workPost : Test
     * workTime : -1
     * postalCode : Test
     * region : test
     * province : Test
     * municipality : test
     * barangay : Test
     * address : Test
     * monthIncome : 1000000
     * employmentCertificate : http://adminimgflb.358fintech.com/FtPdPGYcFnZZfqQCBKI9V43vDgk5
     * theLatestPayroll : http://adminimgflb.358fintech.com/FoB0H06m8MPdSe1X7I3SkUUj0zyA
     * occupationId : 0
     * pId : null
     * companyNature :
     */

    var id: Int = 0
    var userId: Long = 0
    var createTime: Long = 0
    var updateTime: Long = 0
    var companyName: String? = null
    var companyMobile: String? = null
    var workPost: String? = null
    var workTime: Int = 0
    var postalCode: String? = null

    var region: String? = null
    var province: String? = null
    var municipality: String? = null

    var address: String? = null
    var monthIncome: BigDecimal? = null
    var employmentCertificate: String? = null

    var employmentCertificateBack: String? = null
    var theLatestPayroll: String? = null
    var occupationId: Int = 0
    private var pId: Long = 0 //职位父级id
    var companyNature: String? = null
    var salaryMethod: Int = 0
    var paydayOne: Int = 0
    var paydayTwo: Int = 0
    var paydayThree: Int = 0
    var paydayFour: Int = 0
    var performance: Int = 0

    fun getpId(): Long {
        return pId
    }

    fun setpId(pId: Long) {
        this.pId = pId
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
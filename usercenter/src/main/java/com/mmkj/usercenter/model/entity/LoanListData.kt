package com.mmkj.usercenter.model.entity

import com.mmkj.baselibrary.util.DateUtils
import com.mmkj.baselibrary.util.StringUtils

/**
 * @classDesc: 功能描述:(我的借款列表Vo实体)
 * @author: 吴海东
 * @createTime: 2018年9月5日 下午3:05:32
 * @version: v1.0
 * @copyright:秒秒分期网络科技有限公司
 */
class LoanListData {
    //订单号
    var orderNo: String? = null
    //借款时间
    var loadDate: Long = 0
    //借款金额
    var amount: Double = 0.toDouble()
    //借款期限
    var loadPeriod: String? = null
    //还款时间
    var repaymentDate: Long = 0
    //当前欠款
    var debt: Double = 0.toDouble()
    //是否有取款码
    var withdrawalNoStatus: Int = 0

    var withdrawChannel: Int = 0//gcash :6

    fun showRepayDate(): String {
        return DateUtils.showYMDTime(repaymentDate)
    }

    fun showLoanDate(): String {
        return DateUtils.showYMDTime(loadDate)
    }

    fun showAmount(): String {
        return StringUtils.cutOutLastThree(StringUtils.doubleZheng(amount))
    }


}

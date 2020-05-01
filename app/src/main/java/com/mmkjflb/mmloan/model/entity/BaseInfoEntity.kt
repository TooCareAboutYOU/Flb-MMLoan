package com.mmkjflb.mmloan.model.entity

/**
 * @author xuyexiang
 * @date 2018/5/14 14:10
 */

class BaseInfoEntity {
    var userName: String? = null
    var idcard: String? = null
    var idcardFront: String? = null
    var idcardHand: String? = null
    var sex: Int = 0
    var sexValue: String? = null
    var birthday: Long = 0
    var region: String? = null
    var province: String? = null
    var municipality: String? = null
    var education: Int = 0
    var educationValue: String? = null
    var marriage: Int = 0
    var marriageValue: String? = null
    var email: String? = null
    var address: String? = null
    var idcardType: Int = 0
    var surName: String? = null
    var givenName: String? = null
    var middleName: String? = null
    var addressProof: String? = null
    var otherMobilePhone: String? = null
    var addressType: Int = 0
    var addressTypeValue: String? = null
    var ownMobile: Int = -1
    var ownMobileValue: String? = null
    var mobileDuration: Int = 0
    var mobileDurationValue: String? = null

    override fun toString(): String {
        return "BaseInfoEntity(userName=$userName, idcard=$idcard, idcardFront=$idcardFront, idcardHand=$idcardHand, sex=$sex, sexValue=$sexValue, birthday=$birthday, region=$region, province=$province, municipality=$municipality, education=$education, educationValue=$educationValue, marriage=$marriage, marriageValue=$marriageValue, email=$email, address=$address, idcardType=$idcardType, surName=$surName, givenName=$givenName, middleName=$middleName, addressProof=$addressProof, otherMobilePhone=$otherMobilePhone, addressType=$addressType, addressTypeValue=$addressTypeValue, ownMobile=$ownMobile, ownMobileValue=$ownMobileValue, mobileDuration=$mobileDuration, mobileDurationValue=$mobileDurationValue)"
    }


}

package com.mmkjflb.mmloan.model.entity

/**
 * Data: 2018/9/13 15:35
 * Author: Xuyexiang
 * Title:
 */
class FaceRecogniseEntity {
    var message: String? = null
    var data: DataBean? = null
    var code: String? = null

    class DataBean {
        var score: String? = null
    }
}

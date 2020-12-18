package com.crimson.imageloader.config

import com.crimson.imageloader.annotation.LottieLoadType

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: lottie加载图片配置
 **/
data class LottieImageConfig(
    @LottieLoadType
    var type: Int? = 0,
    //包括net url 和本地assets 或json string 根据type加载
    var assets: String? = "",
    //当type 为row_res 时，会从这个值里面取，需要赋值
    var rowRes: Int? = 0,
    //是否循环动画
    var isRepeatAnim: Boolean? = true
)
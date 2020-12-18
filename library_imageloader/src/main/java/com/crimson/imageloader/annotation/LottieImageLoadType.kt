package com.crimson.imageloader.annotation

import androidx.annotation.IntDef

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: lottie 图片加载类型
 **/
object LottieImageLoadType {
    //网络
    const val NETWORK = 0

    //本地资源 放在assets目录下
    const val ASSETS = 1

    //本地资源 放在raw目录下
    const val RAW_RES = 2

    //json string对象
    const val JSON_STRING = 3
}

@IntDef(
    LottieImageLoadType.NETWORK,
    LottieImageLoadType.ASSETS,
    LottieImageLoadType.RAW_RES,
    LottieImageLoadType.JSON_STRING
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class LottieLoadType
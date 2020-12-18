package com.crimson.imageloader.annotation

import androidx.annotation.IntDef

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: image 请求优先级 从0到3依次降低
 **/
object ImageLoadPriority {

    const val IMMEDIATE = 0
    const val HIGH = 1
    const val NORMAL = 2
    const val LOW = 3
}

@IntDef(
    ImageLoadPriority.IMMEDIATE,
    ImageLoadPriority.HIGH,
    ImageLoadPriority.NORMAL,
    ImageLoadPriority.LOW
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class ImagePriority
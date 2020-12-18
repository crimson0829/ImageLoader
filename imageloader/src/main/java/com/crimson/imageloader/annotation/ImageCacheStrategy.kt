package com.crimson.imageloader.annotation

import androidx.annotation.IntDef
import com.crimson.imageloader.annotation.ImageCacheStrategy.ALL
import com.crimson.imageloader.annotation.ImageCacheStrategy.AUTOMATIC
import com.crimson.imageloader.annotation.ImageCacheStrategy.DATA
import com.crimson.imageloader.annotation.ImageCacheStrategy.NONE
import com.crimson.imageloader.annotation.ImageCacheStrategy.RESOURCE


/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: 图片缓存策略，对应5种缓存策略
 **/
object ImageCacheStrategy {

    const val ALL = 0
    const val NONE = 1
    const val RESOURCE = 2
    const val DATA = 3
    const val AUTOMATIC = 4

}

@IntDef(ALL, NONE, RESOURCE, DATA, AUTOMATIC)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class CacheStrategy
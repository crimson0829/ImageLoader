package com.crimson.imageloader

import android.content.Context
import com.crimson.imageloader.config.ImageConfig

/**
 * Author: crimson
 * Date: 2020/12/15
 * Description: image loader 接口
 **/
interface IImageLoader<T : ImageConfig?> {

    /**
     * 加载图片
     */
    fun loadImage(context: Context?, config: T?)

    /**
     * 释放 如果需要，可以在 activity destroy 的时候调用
     */
    fun release(context: Context?, config: T?)

}
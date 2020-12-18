package com.crimson.imageloader

import android.content.Context
import com.crimson.imageloader.config.ImageConfig

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: 图片加载策略
 **/
interface IImageLoaderStrategy<T : ImageConfig?> {

    /**
     * 加载图片
     *
     * @param ctx    [Context]
     * @param config 图片加载配置信息
     */
    fun loadImage(ctx: Context?, config: T?)

    /**
     * 释放
     *
     * @param ctx    [Context]
     * @param config 图片加载配置信息
     */
    fun release(ctx: Context?, config: T?)
}
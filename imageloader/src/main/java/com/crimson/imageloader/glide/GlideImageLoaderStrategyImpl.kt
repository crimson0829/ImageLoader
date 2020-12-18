package com.crimson.imageloader.glide

import android.content.Context
import com.crimson.imageloader.IImageLoaderStrategy
import com.crimson.imageloader.config.ImageConfigImpl

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description:glide策略实现
 **/
class GlideImageLoaderStrategyImpl : IImageLoaderStrategy<ImageConfigImpl> {

   private val configLoader by lazy { GlideImageConfigLoader() }

    /**
     * 加载图片实现
     * @param config config 实现
     */
    override fun loadImage(ctx: Context?, config: ImageConfigImpl?) {
        configLoader.load(ctx, config)
    }

    /**
     * 停止正在请求加载的图片
     */
    override fun release(ctx: Context?, config: ImageConfigImpl?) {
        configLoader.release(ctx, config)

    }
}
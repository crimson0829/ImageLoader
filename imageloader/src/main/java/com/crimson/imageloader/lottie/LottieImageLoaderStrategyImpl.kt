package com.crimson.imageloader.lottie

import android.content.Context
import com.crimson.imageloader.IImageLoaderStrategy
import com.crimson.imageloader.config.ImageConfigImpl

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: lottie策略的image实现
 **/
class LottieImageLoaderStrategyImpl : IImageLoaderStrategy<ImageConfigImpl> {

    private val loader by lazy { LottieImageConfigLoader() }


    override fun loadImage(ctx: Context?, config: ImageConfigImpl?) {
        loader.load(ctx, config)
    }

    override fun release(ctx: Context?, config: ImageConfigImpl?) {
        loader.release(ctx, config)
    }
}
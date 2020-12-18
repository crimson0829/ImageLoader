package com.crimson.imageloader

import android.content.Context
import com.crimson.imageloader.config.ImageConfigImpl
import com.crimson.imageloader.glide.GlideImageLoaderStrategyImpl
import com.crimson.imageloader.lifecycle.bindLifecycle
import com.crimson.imageloader.lottie.LottieImageLoaderStrategyImpl

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: ImageLoader 实现
 **/
open class ImageLoaderImpl : IImageLoader<ImageConfigImpl> {

    /**
     * 策略实现，交给策略去处理图片加载
     */
    protected var strategy: IImageLoaderStrategy<ImageConfigImpl>? = null

    override fun loadImage(context: Context?, config: ImageConfigImpl?) {

        config?.apply {

            val loadFromLottie = isLoadFromLottie()
            strategy = if (loadFromLottie) {
                //使用Lottie策略实现
                LottieImageLoaderStrategyImpl()
            } else {
                //使用glide策略实现
                GlideImageLoaderStrategyImpl()
            }

            if (isBindLifecycle) {
                //绑定生命周期
                bindLifecycle(context, config)
            }

        }

        this.strategy?.loadImage(context, config)
    }

    override fun release(context: Context?, config: ImageConfigImpl?) {
        this.strategy?.release(context, config)

    }

}
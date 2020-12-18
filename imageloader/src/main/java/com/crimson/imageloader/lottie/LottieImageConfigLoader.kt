package com.crimson.imageloader.lottie

import android.content.Context
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieTask
import com.crimson.imageloader.annotation.LottieImageLoadType
import com.crimson.imageloader.config.ImageConfigImpl

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: 使用lottie加载，目前简单的lottie 配置实现，如果想增加功能，可以配合[LottieImageConfig]在该类中添加
 **/
class LottieImageConfigLoader {

    /**
     * 加载lottie图片
     */
    fun load(ctx: Context?, config: ImageConfigImpl?) {

        if (ctx == null) {
            return
        }

        config?.apply {

            if (lottieImageConfig == null) {
                //config为空就直接从网络url加载
                LottieCompositionFactory.fromUrl(ctx, imgUrl)
                    .addListener {
                        if (imageView is LottieAnimationView) {
                            val lottieView = imageView as LottieAnimationView
                            lottieView.setComposition(it)
                            lottieView.repeatCount = -1
                            lottieView.playAnimation()
                        }
                    }
            }

            //如果设置了 lottieImageConfig，就从config加载，一般配合isBuildFromLottie=true使用
            lottieImageConfig?.let { lottieConfig ->
                val lottieTask: LottieTask<LottieComposition>

                when (lottieConfig.type) {
                    LottieImageLoadType.NETWORK -> {
                        //网络获取
                        lottieTask = LottieCompositionFactory.fromUrl(ctx, lottieConfig.assets)
                    }
                    LottieImageLoadType.ASSETS -> {
                        //本地资源assets目录获取
                        lottieTask = LottieCompositionFactory.fromAsset(ctx, lottieConfig.assets)

                    }
                    LottieImageLoadType.RAW_RES -> {
                        //本地资源 row目录获取
                        lottieTask = LottieCompositionFactory.fromRawRes(
                            ctx,
                            lottieConfig.rowRes ?: 0,
                            lottieConfig.rowRes.hashCode().toString()
                        )

                    }
                    LottieImageLoadType.JSON_STRING -> {
                        //json string 获取
                        lottieTask = LottieCompositionFactory.fromJsonString(
                            lottieConfig.assets,
                            lottieConfig.assets.hashCode().toString()
                        )

                    }
                    else -> {
                        //默认从网络获取
                        lottieTask = LottieCompositionFactory.fromUrl(ctx, lottieConfig.assets)
                    }
                }

                lottieTask.addListener {
                    if (imageView is LottieAnimationView) {
                        val lottieView = imageView as LottieAnimationView
                        lottieView.setComposition(it)
                        lottieView.repeatCount =
                            if (lottieConfig.isRepeatAnim == true) -1 else 0
                        lottieView.playAnimation()
                    }

                }
            }


        }

    }

    /**
     * 释放操作，销毁
     */
    fun release(ctx: Context?, config: ImageConfigImpl?) {

        if (ctx == null) {
            return
        }

        config?.apply {
            if (imageView is LottieAnimationView) {
                (imageView as LottieAnimationView).cancelAnimation()
            }
        }

    }
}
package com.crimson.imageloader.ext

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.crimson.imageloader.ImageLoaderImpl
import com.crimson.imageloader.R
import com.crimson.imageloader.annotation.LottieImageLoadType
import com.crimson.imageloader.annotation.LottieLoadType
import com.crimson.imageloader.config.ImageConfigImpl
import com.crimson.imageloader.config.LottieImageConfig

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: 封装了一些常用的扩展函数，方便调用,如果有什么需求，可以在这里扩展
 **/

/**
 * 加载图片 设置config版,配置信息都可以通过ImageConfigImpl 设置
 */
fun ImageView.loadImage(context: Context?, config: ImageConfigImpl?) {
    val loader = ImageLoaderImpl()
    loader.loadImage(context, config)
}

/**
 * 加载图片 简单版,只设置url加载
 */
fun ImageView.loadImageSimple(url: String?) {
    loadImage(
        context, ImageConfigImpl.builder()?.url(url)?.imageView(this)?.build()
    )
}

/**
 * 加载图片普通默认版 添加了placeholder error等
 */
fun ImageView.loadImage(url: String?) {
    loadImage(
        context, ImageConfigImpl.builder()
            ?.url(url)
            ?.imageView(this)
            ?.placeholder(R.drawable.imageload_def_color)
            ?.errorPic(R.drawable.imageload_def_color)
            ?.build()
    )
}

/**
 * 加载圆角图
 */
fun ImageView.loadImageRoundCorner(url: String?, radius: Int?) {

    loadImage(
        context, ImageConfigImpl.builder()
            ?.url(url)
            ?.imageView(this)
            ?.imageRadius(radius ?: 0)
            ?.build()
    )

}

/**
 * load as bitmap
 */
fun ImageView.loadImageAsBitmap(
    url: String?, loadSucBlock: (Bitmap) -> Unit = { _ -> },
    loadErrorBlock: (Exception) -> Unit = { _ -> }
) {

    loadImage(context, ImageConfigImpl.builder()
        ?.url(url)
        ?.isLoadAsBitmap(true)
        ?.loadBitmapSuccessAction {
            loadSucBlock.invoke(it)
        }
        ?.loadBitmapErrorAction {
            loadErrorBlock.invoke(it)
        }
        ?.build())

}


/**
 * 使用lottie加载图片
 * @param type 加载类型 [LottieImageLoadType]
 * @param assets 可以是网络url，本地assets 或 json string,根据type取
 * @param rowRes 本地row资源
 * @param isRepeatAnim 是否循环
 */
fun ImageView.loadImageFromLottie(
    @LottieLoadType
    type: Int? = LottieImageLoadType.NETWORK,
    assets: String? = "",
    rowRes: Int? = 0,
    isRepeatAnim: Boolean? = true
) {

    loadImageFromLottie(LottieImageConfig(type, assets, rowRes, isRepeatAnim))
}

/**
 * 根据lottie配置加载图片
 */
fun ImageView.loadImageFromLottie(config: LottieImageConfig?) {

    loadImage(
        context, ImageConfigImpl.builder()
            ?.imageView(this)
            ?.isBuildFromLottie(true)
            ?.buildLottieConfig(config)
            ?.build()
    )

}






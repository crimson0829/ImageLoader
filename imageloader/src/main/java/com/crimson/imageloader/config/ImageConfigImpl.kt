package com.crimson.imageloader.config

import android.graphics.Bitmap
import android.widget.ImageView
import com.crimson.imageloader.annotation.CacheStrategy
import com.crimson.imageloader.annotation.ImagePriority
import com.crimson.imageloader.utils.ImageUrlUtils
import kotlin.Exception

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: 图片配置实现类
 **/
class ImageConfigImpl constructor(builder: Builder) : ImageConfig() {

    //缓存策略
    @CacheStrategy
    var cacheStrategy = 0

    //请求优先级
    @ImagePriority
    var loadPriority = 0

    //请求 url 为空,则使用此图片作为占位符
    var fallback = 0

    //图片每个圆角的大小
    var imageRadius = 0

    //image view数组
    var imageViews: Array<out ImageView>? = null

    //是否使用淡入淡出过渡动画
    var isCrossFade = false

    //是否将图片剪切为 CenterCrop
    var isCenterCrop = false

    //加载类型是否为 isFitCenter
    var isFitCenter = false

    //是否将图片剪切为圆形
    var isCircle = false

    //高斯模糊值
    var imageBlur = 0

    //清理内存缓存
    var isClearMemory = false

    //清理本地缓存
    var isClearDiskCache = false

    //是否以bitmap方式加载返回,
    // 如果该字段设置了true，就需设置loadBitmapSuccessBlock和loadBitmapErrorBlock回调bitmap
    var isLoadAsBitmap = false

    //转换bitmap成功高阶
    var loadBitmapSuccessBlock: (Bitmap) -> Unit = { _ -> }

    //转换bitmap失败高阶
    var loadBitmapErrorBlock: (Exception) -> Unit = { _ -> }

    //设置是否从lottie加载，可以强制配置
    var isBuildFromLottie = false

    //lottie 配置
    var lottieImageConfig: LottieImageConfig? = null

    //绑定生命周期,默认为绑定
    var isBindLifecycle: Boolean = true

    init {
        this.imgUrl = builder.url
        this.imageView = builder.imageView
        this.placeholder = builder.placeholder
        this.errorPic = builder.errorPic
        this.fallback = builder.fallback
        this.cacheStrategy = builder.cacheStrategy
        this.loadPriority = builder.loadPriority
        this.imageRadius = builder.imageRadius
        this.imageViews = builder.imageViews
        this.isCrossFade = builder.isCrossFade
        this.isCenterCrop = builder.isCenterCrop
        this.isFitCenter = builder.isFitCenter
        this.isCircle = builder.isCircle
        this.imageBlur = builder.imageBlur
        this.isClearMemory = builder.isClearMemory
        this.isClearDiskCache = builder.isClearDiskCache
        this.isLoadAsBitmap = builder.isLoadAsBitmap
        this.loadBitmapSuccessBlock = builder.loadBitmapSuccessBlock
        this.loadBitmapErrorBlock = builder.loadBitmapErrorBlock
        this.isBuildFromLottie = builder.isBuildFromLottie
        this.lottieImageConfig = builder.lottieImageConfig
        this.isBindLifecycle = builder.isBindLifecycle
    }

    companion object {
        /**
         * 默认构建Builder
         */
        fun builder(): Builder? {
            return Builder()
        }
    }

    /**
     * 是否从lottie加载
     */
    fun isLoadFromLottie(): Boolean {
        return isBuildFromLottie || ImageUrlUtils.checkUrlEndWithJson(imgUrl ?: "")
    }

    class Builder {

        internal var url: String? = ""
        internal var imageView: ImageView? = null
        internal var placeholder = 0
        internal var errorPic = 0
        internal var fallback = 0

        @CacheStrategy
        internal var cacheStrategy = 0

        @ImagePriority
        internal var loadPriority = 0

        internal var imageRadius = 0

        internal var imageViews: Array<out ImageView>? = null
        internal var isCrossFade = false
        internal var isCenterCrop = false
        internal var isFitCenter = false
        internal var isCircle = false
        internal var imageBlur = 0
        internal var isClearMemory = false
        internal var isClearDiskCache = false
        internal var isLoadAsBitmap = false
        internal var loadBitmapSuccessBlock: (Bitmap) -> Unit = { _ -> }
        internal var loadBitmapErrorBlock: (Exception) -> Unit = { _ -> }
        internal var lottieImageConfig: LottieImageConfig? = null
        internal var isBuildFromLottie = false
        internal var isBindLifecycle = true


        fun url(url: String?): Builder {
            this.url = url
            return this
        }

        fun placeholder(placeholder: Int): Builder {
            this.placeholder = placeholder
            return this
        }

        fun errorPic(errorPic: Int): Builder {
            this.errorPic = errorPic
            return this
        }

        fun fallback(fallback: Int): Builder {
            this.fallback = fallback
            return this
        }

        fun imageView(imageView: ImageView?): Builder {
            this.imageView = imageView
            return this
        }

        fun cacheStrategy(@CacheStrategy cacheStrategy: Int): Builder {
            this.cacheStrategy = cacheStrategy
            return this
        }

        fun loadPriority(@ImagePriority priority: Int): Builder {
            this.loadPriority = priority
            return this
        }

        fun imageRadius(imageRadius: Int): Builder {
            this.imageRadius = imageRadius
            return this
        }


        fun imageViews(vararg imageViews: ImageView): Builder {
            this.imageViews = imageViews
            return this
        }

        fun isCrossFade(isCrossFade: Boolean): Builder {
            this.isCrossFade = isCrossFade
            return this
        }

        fun isCenterCrop(isCenterCrop: Boolean): Builder {
            this.isCenterCrop = isCenterCrop
            return this
        }

        fun isFitCenter(isFitCenter: Boolean): Builder {
            this.isFitCenter = isFitCenter
            return this
        }

        fun isCircle(isCircle: Boolean): Builder {
            this.isCircle = isCircle
            return this
        }

        fun imageBlur(blurValue: Int): Builder {
            this.imageBlur = blurValue
            return this
        }

        fun isClearMemory(isClearMemory: Boolean): Builder {
            this.isClearMemory = isClearMemory
            return this
        }


        fun isClearDiskCache(isClearDiskCache: Boolean): Builder {
            this.isClearDiskCache = isClearDiskCache
            return this
        }

        fun isLoadAsBitmap(isLoadAsBitmap: Boolean): Builder {
            this.isLoadAsBitmap = isLoadAsBitmap
            return this
        }

        fun loadBitmapSuccessAction(loadBitmapSuccessBlock: (Bitmap) -> Unit): Builder {
            this.loadBitmapSuccessBlock = loadBitmapSuccessBlock
            return this
        }

        fun loadBitmapErrorAction(loadBitmapErrorBlock: (Exception) -> Unit): Builder {
            this.loadBitmapErrorBlock = loadBitmapErrorBlock
            return this
        }

        fun isBuildFromLottie(isLoadFromLottie: Boolean): Builder {
            this.isBuildFromLottie = isLoadFromLottie
            return this
        }

        fun buildLottieConfig(lottieImageConfig: LottieImageConfig?): Builder {
            this.lottieImageConfig = lottieImageConfig
            return this
        }

        fun bindLifecycle(isBindLifecycle: Boolean = true): Builder {
            this.isBindLifecycle = isBindLifecycle
            return this
        }

        fun build(): ImageConfigImpl {
            return ImageConfigImpl(this)
        }
    }


}
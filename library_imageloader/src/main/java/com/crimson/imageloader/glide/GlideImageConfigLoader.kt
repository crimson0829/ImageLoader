package com.crimson.imageloader.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition
import com.crimson.imageloader.config.ImageConfigImpl
import com.crimson.imageloader.annotation.ImageCacheStrategy
import com.crimson.imageloader.annotation.ImageLoadPriority
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: 根据config 使用glide 加载
 **/
class GlideImageConfigLoader {

    /**
     * 加载 图片实现
     */
    fun load(ctx: Context?, config: ImageConfigImpl?) {

        if (ctx == null) {
            return
        }

        val manager = Glide.with(ctx)

        config?.apply {

            val request = if (isLoadAsBitmap) {
                manager.asBitmap()
                    .load(imgUrl)

            } else {
                manager.load(imgUrl)

            }

            //缓存策略
            when (cacheStrategy) {
                ImageCacheStrategy.ALL -> request.diskCacheStrategy(DiskCacheStrategy.ALL)
                ImageCacheStrategy.NONE -> request.diskCacheStrategy(DiskCacheStrategy.NONE)
                ImageCacheStrategy.RESOURCE -> request.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                ImageCacheStrategy.DATA -> request.diskCacheStrategy(DiskCacheStrategy.DATA)
                ImageCacheStrategy.AUTOMATIC -> request.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            }

            //请求优先级
            when (loadPriority) {
                ImageLoadPriority.IMMEDIATE -> request.priority(Priority.IMMEDIATE)
                ImageLoadPriority.HIGH -> request.priority(Priority.HIGH)
                ImageLoadPriority.NORMAL -> request.priority(Priority.NORMAL)
                ImageLoadPriority.LOW -> request.priority(Priority.LOW)
            }

            //设置请求为空图片
            if (fallback != 0) {
                request.fallback(fallback)
            }

            //image radius
            if (imageRadius > 0) {
                request.transform(RoundedCornersTransformation(imageRadius, 0))
            }

            //淡入淡出动画
            if (isCrossFade) {
                (request as? RequestBuilder<Drawable>)
                    ?.transition(
                        DrawableTransitionOptions.with(
                            DrawableCrossFadeFactory
                                .Builder(200)
                                .setCrossFadeEnabled(true)
                                .build()
                        )
                    )

            }

            if (isCenterCrop) {
                request.centerCrop()
            }

            if (isFitCenter) {
                request.fitCenter()
            }

            if (isCircle) {
                request.circleCrop()
            }

            if (imageBlur > 0) {
                request.transform(BlurTransformation(imageBlur, 5))
            }

            placeholder?.let {
                if (it != 0) {
                    request.placeholder(it)
                }
            }

            errorPic?.let {
                if (it != 0) {
                    request.error(it)
                }
            }


            if (isLoadAsBitmap) {
                (request as? RequestBuilder<Bitmap>)?.into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        loadBitmapSuccessBlock.invoke(resource)

                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        loadBitmapErrorBlock.invoke(GlideException("加载失败"))
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
            } else {
                imageView?.let {
                    request.into(it)
                }
            }


        }
    }

    /**
     * 释放
     */
    fun release(ctx: Context?, config: ImageConfigImpl?) {

        if (ctx == null) {
            return
        }

        config?.apply {

            imageView?.let {
                Glide.get(ctx).requestManagerRetriever.get(ctx).clear(it)
            }

            imageViews?.let {
                if (it.isNotEmpty()) {
                    it.forEach { image ->
                        Glide.get(ctx).requestManagerRetriever.get(ctx).clear(image)
                    }
                }
            }

            if (isClearDiskCache) {
                Completable.fromAction {
                    Glide.get(ctx)
                        .clearDiskCache()
                }.subscribeOn(Schedulers.io()).subscribe()
            }

            if (isClearMemory) {
                Completable.fromAction { Glide.get(ctx).clearMemory() }.subscribeOn(
                    AndroidSchedulers.mainThread()
                ).subscribe()
            }

        }
    }


}
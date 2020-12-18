package com.crimson.imageloader.lifecycle

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.crimson.imageloader.config.ImageConfigImpl
import kotlin.reflect.KFunction2

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: ImageLoader 生命周期绑定，在destroy的时候调用clear方法
 **/
class ImageLoaderLifecycleObserver(
    private val release: KFunction2<Context?, ImageConfigImpl?, Unit>,
    private val context: Context?,
    private val config: ImageConfigImpl?
) : LifecycleObserver {

    /**
     * 在销毁的时候调用release方法
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() = release.invoke(context, config)
}
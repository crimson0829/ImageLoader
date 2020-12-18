package com.crimson.imageloader.lifecycle

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.crimson.imageloader.ImageLoaderImpl
import com.crimson.imageloader.config.ImageConfigImpl

/**
 * Author: crimson
 * Date: 2020/12/17
 * Description:绑定生命周期扩展
 **/

/**
 * 绑定生命周期 在LifecycleOwner 销毁的时候执行release方法
 */
fun ImageLoaderImpl.bindLifecycle(context: Context?, config: ImageConfigImpl?) {
    if (context is LifecycleOwner) {
        val observer = ImageLoaderLifecycleObserver(::release, context, config)
        context.lifecycle.addObserver(observer)
    }
}
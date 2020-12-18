package com.crimson.imageloader.config

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: image 配置类,设置一些配置信息
 **/
open class ImageConfig {

    //image load url
    var imgUrl: String? = ""

    //load view
    var imageView: ImageView? = null

    //占位符
    @DrawableRes
    var placeholder: Int? = 0

    //错误图片
    @DrawableRes
    var errorPic: Int? = 0
}
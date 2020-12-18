package com.crimson.imageloader.utils

/**
 * Author: crimson
 * Date: 2020/12/16
 * Description: image url  工具类
 **/
object ImageUrlUtils {

    /**
     * 检查url是否以.json 结尾
     */
    fun checkUrlEndWithJson(url: String): Boolean = url.endsWith(".json")

}
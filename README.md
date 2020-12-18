# ImageLoader


#### 简介：
一个结合 Glide 与 Lottie 框架的图片加载组件，绑定生命周期，利于回收，使用简单。


#### 引入

```

implementation 'com.github.crimson0829:ImageLoader:1.0.3'

```

#### 使用：

1.标准使用

```
 //1.创建实现类
 val loader = ImageLoaderImpl()

 //2.创建ImageConfigImpl设置具体加载信息，你可以任意配置你想要显示的信息
 loader.loadImage(context: Context?, config: ImageConfigImpl?)

 //ImageConfigImpl 配置使用，其他参数后面列表说明
 ImageConfigImpl.builder()
            ?.url(url)
            ?.imageView(imageView)
            ?.placeholder()
            ?.errorPic()
            ?.build()
```

2.快速使用，扩展了ImageView，供简单调用

```
//1.默认使用
fun ImageView.loadImage(url: String?)

//2.加载圆角图
fun ImageView.loadImageRoundCorner(url: String?, radius: Int?)

//3.转换成bitmap加载
fun ImageView.loadImageAsBitmap(
    url: String?,
    loadSucBlock: (Bitmap) -> Unit = { _ -> },
    loadErrorBlock: (Exception) -> Unit = { _ -> }
)

//lottie 的使用控件需是 LottieAnimationView
//4.使用lottie加载图片
fun ImageView.loadImageFromLottie(
    @LottieLoadType
    type: Int? = LottieImageLoadType.NETWORK,
    assets: String? = "",
    rowRes: Int? = 0,
    isRepeatAnim: Boolean? = true
)

//5.根据lottie配置加载图片
fun ImageView.loadImageFromLottie(config: LottieImageConfig?)

```

3.ImageConfigImpl 参数说明

|          参数          |  类型   |                             说明                             |
| :--------------------: | :-----: | :----------------------------------------------------------: |
|          url           | String  |                           加载url                            |
|       imageView        | Object  |                      ImageView加载对象                       |
|      placeholder       |   Int   |                          图片占位符                          |
|        errorPic        |   Int   |                        错误图片占位符                        |
|        fallback        |   Int   |                      _设置url为空图片_                       |
|     cacheStrategy      |   Int   |                           缓存策略                           |
|      loadPriority      |   Int   |                          加载优先级                          |
|      imageRadius       |   Int   |                           图片圆角                           |
|       imageViews       |  Array  |                   释放时ImageView设置对象                    |
|      isCrossFade       | Boolean |                     是否开启淡入淡出动画                     |
|      isCenterCrop      | Boolean |                   加载模式是否为CenterCrop                   |
|      isFitCenter       | Boolean |                   加载模式是否为FitCenter                    |
|        isCircle        | Boolean |                        是否加载圆形图                        |
|       imageBlur        |   Int   |                        设置高斯模糊值                        |
|     isClearMemory      | Boolean |                   在释放时是否清除内存缓存                   |
|    isClearDiskCache    | Boolean |                   在释放时是否清除磁盘缓存                   |
|     isLoadAsBitmap     | Boolean |                     是否转换成Bitmap加载                     |
| loadBitmapSuccessBlock | Object  |                      转换Bitmap成功回调                      |
|  loadBitmapErrorBlock  | Object  |                      转换Bitmap失败回调                      |
|   lottieImageConfig    | Object  | lottie图片配置：<br />type -> 加载模式<br />assets -> 加载资源(包括url、assets和 json string)<br />rawRes -> 记载raw资源<br />isRepeatAnim ->是否循环动画 |
|   isBuildFromLottie    | Boolean |                      是否使用lottie加载                      |
|    isBindLifecycle     | Boolean |                 是否绑定生命周期，默认为开启                 |

4.ImageConfigImpl 具体参数代码调用

```
ImageConfigImpl.builder()
            ?.url(url)//设置url
            ?.imageView(imageView)//设置imageView
            ?.placeholder(res)//占位符
            ?.errorPic(res)//错误占位符
            ?.fallback(res)//url为空占位符
            ?.cacheStrategy(ImageCacheStrategy.ALL)//缓存策略
            ?.loadPriority(ImageLoadPriority.IMMEDIATE)//加载策略
            ?.imageRadius(5)//圆角大小
            ?.imageViews(array)//释放时imageView数组
            ?.isCrossFade(true)//淡入淡出动画
            ?.isCenterCrop(true)//CenterCrop模式
            ?.isFitCenter(false)//FitCenter模式
            ?.imageBlur(0)//高斯模糊值
            ?.isClearMemory(false)//释放时是否清空内存缓存
            ?.isClearDiskCache(false)//释放时是否清空磁盘缓存
            ?.isLoadAsBitmap(false)//是否转换成Bitmap加载
            ?.loadBitmapSuccessAction {  }//转换Bitmap成功回调
            ?.loadBitmapErrorAction {  }//转换Bitmap失败回调
            ?.isBuildFromLottie(false)//是否从lottie加载
            //配置lottie属性
            ?.buildLottieConfig(LottieImageConfig(type = LottieImageLoadType.NETWORK,assets = url,rowRes = res,isRepeatAnim = true))
            ?.bindLifecycle(true)//绑定生命周期
            ?.build()
```


#### 扩展：

1.属性扩展：可直接在 ImageConfigImpl 中添加扩展属性，之后在目前实现的对应策略中实现；<br />glide策略 -> GlideImageConfigLoader<br />lottie策略 -> LottieImageConfigLoader<br />2.策略扩展：<br />1）实现 IImageLoaderStrategy 生成对应策略<br />2）将对应策略实现添加到 ImageLoaderImpl 中<br />
<br />





## License

```
Copyright 2020 crimson0829
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



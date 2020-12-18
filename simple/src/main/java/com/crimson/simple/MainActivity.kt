package com.crimson.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.crimson.imageloader.ImageLoaderImpl
import com.crimson.imageloader.annotation.LottieImageLoadType
import com.crimson.imageloader.config.ImageConfigImpl
import com.crimson.imageloader.config.LottieImageConfig
import com.crimson.imageloader.ext.loadImage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val url = "https://img.xjh.me/random_img.php?type=bg&ctype=nature&return=302&device=mobile"
    val lottieUrl = "https://assets7.lottiefiles.com/packages/lf20_c7bjpagm.json"

    val imageLoader by lazy { ImageLoaderImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //快递调用
        iv_image.loadImage(url)

        tv_test.setOnClickListener {

            MaterialDialog(this).show {
                listItems(R.array.testApis) { _, index, _ ->
                    applyApi(index)


                }
            }

        }

    }

    /**
     * api test
     */
    private fun applyApi(index: Int) {
        when (index) {
            0 -> {
                //imageRadius
                imageLoader.loadImage(
                    this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.imageRadius(20)
                        ?.build()
                )

            }
            1 -> {
                //isCrossFade
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isCrossFade(true)
                        ?.build()
                )

            }
            2 -> {
                //isCenterCrop
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isCenterCrop(true)
                        ?.build()
                )
            }
            3 -> {
                //isFitCenter
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isFitCenter(true)
                        ?.build()
                )
            }
            4 -> {
                //isCircle
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isCircle(true)
                        ?.build()
                )
            }
            5 -> {
                //imageBlur
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.imageBlur(5)
                        ?.build()
                )
            }
            6 -> {
                //isLoadAsBitmap
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isLoadAsBitmap(true)
                        ?.loadBitmapSuccessAction {
                            iv_image.setImageBitmap(it)
                        }
                        ?.loadBitmapErrorAction {  }
                        ?.build()
                )

            }
            7 -> {
                //buildLottieFromUrl
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isBuildFromLottie(true)
                        ?.buildLottieConfig(
                            LottieImageConfig(
                                LottieImageLoadType.NETWORK,
                                lottieUrl
                            )
                        )
                        ?.build()
                )
            }
            8 -> {
                //buildLottieFromAssets
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isBuildFromLottie(true)
                        ?.buildLottieConfig(
                            LottieImageConfig(
                                LottieImageLoadType.ASSETS,
                                "AndroidWave.json"
                            )
                        )
                        ?.build()
                )
            }

            9->{
                //buildLottieFromRaw
                imageLoader.loadImage(this,
                    ImageConfigImpl.builder()
                        ?.url(url)
                        ?.imageView(iv_image)
                        ?.isBuildFromLottie(true)
                        ?.buildLottieConfig(
                            LottieImageConfig(
                                LottieImageLoadType.RAW_RES,
                                rowRes = R.raw.lottielogo
                            )
                        )
                        ?.build()
                )
            }
        }
    }
}
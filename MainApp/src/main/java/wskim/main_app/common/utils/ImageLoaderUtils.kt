package wskim.main_app.common.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import wskim.main_app.R

object ImageLoaderUtils {

    private fun dpToPx(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }

    fun defaultContentImage(v: ImageView, path: String){
        Glide.with(v.context)
            .load(path)
            .centerCrop()
            .error(R.drawable.ic_launcher_foreground)
            .transform(CenterCrop(), RoundedCorners(dpToPx(v.context, 7)))
            .into(v)
    }

//    fun userSmallProfile(v: ImageView, path: String) {
//        Glide.with(v.context)
//            .load(BuildConfig.IMAGE_SERVER_URL + path + BuildConfig.IMAGE_FORMAT_128)
//            .circleCrop()
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .error(R.drawable.common_icon_body_join_perosn_png)
//            .into(v)
//    }
//
//    fun productSmallImage(v: ImageView, path: String){
//        Glide.with(v.context)
//            .load(BuildConfig.IMAGE_SERVER_URL + path + BuildConfig.IMAGE_FORMAT_128)
//            .centerCrop()
//            .error(R.drawable.chat_list_view_icon_picture_png)
//            .transform(CenterCrop(), RoundedCorners(dpToPx(v.context, 7)))
//            .into(v)
//    }
//
//    fun productMiddleImage(v: ImageView, path: String){
//        Glide.with(v.context)
//            .load(BuildConfig.IMAGE_SERVER_URL + path + BuildConfig.IMAGE_FORMAT_512)
//            .centerCrop()
//            .error(R.drawable.chat_list_view_icon_picture_png)
//            .transform(CenterCrop(), RoundedCorners(dpToPx(v.context, 7)))
//            .into(v)
//    }
//
//    fun productFullImage(v: ImageView, path: String){
//        Glide.with(v.context)
//            .load(BuildConfig.IMAGE_SERVER_URL + path + BuildConfig.IMAGE_FORMAT_1024)
//            .centerCrop()
//            .error(R.drawable.chat_list_view_icon_picture_png)
//            .transform(CenterCrop(), RoundedCorners(dpToPx(v.context, 7)))
//            .into(v)
//    }
//
//    fun productSlideImage(v: ImageView, path: String){
//        Glide.with(v.context)
//            .load(BuildConfig.IMAGE_SERVER_URL + path + BuildConfig.IMAGE_FORMAT_1024)
//            .error(R.drawable.chat_list_view_icon_picture_png)
//            .into(v)
//    }
//
//    fun homeBannerImage(v: ImageView, path: String){
//        Glide.with(v.context)
//            .load(BuildConfig.IMAGE_SERVER_URL + path + BuildConfig.IMAGE_FORMAT_1024)
//            .centerCrop()
//            .error(R.drawable.chat_list_view_icon_picture_png)
//            .into(v)
//    }
//
//    fun categorySmallIcon(v: ImageView, path: String){
//        Glide.with(v.context)
//            .load(BuildConfig.IMAGE_SERVER_URL + path + BuildConfig.IMAGE_FORMAT_256)
//            .error(R.drawable.chat_list_view_icon_picture_png)
//            .into(v)
//    }
}
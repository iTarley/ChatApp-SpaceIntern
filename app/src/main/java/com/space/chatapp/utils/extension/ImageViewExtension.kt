package com.space.chatapp.utils.extension

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

fun ImageView.getDrawable(context: Context, drawableRes: Int) {
    background = AppCompatResources.getDrawable(context, drawableRes)
}
fun ImageView.setImageTint(colorRes:Int){
    this.imageTintList = ColorStateList.valueOf(this.context.getColor(colorRes))
}
package com.space.chatapp.utils.extension

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.setTint(colorRes:Int){
    this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(colorRes))
}
fun View.setBackgroundColorRes(@ColorRes colorRes: Int) {
    val color = ContextCompat.getColor(context, colorRes)
    setBackgroundColor(color)
}
fun TextView.setTextColorCompat(@ColorRes colorRes: Int) {
        this.setTextColor(context.getColor(colorRes))
}


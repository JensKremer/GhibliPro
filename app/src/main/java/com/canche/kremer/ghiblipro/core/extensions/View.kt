package com.canche.kremer.ghiblipro.core.extensions

import android.content.Context
import android.view.View
import android.view.View.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat

fun isGone(isIt: Boolean) = when(isIt){
    true -> GONE
    false -> VISIBLE
}

fun View.rotateAnim(angle :Float ,drawableEnd: Int, duration : Long, context: Context) {
    animate().apply {
        setDuration(duration)
        rotation(angle)
    }.withEndAction {
        (this as ImageView).setImageDrawable(ContextCompat.getDrawable(context,drawableEnd))

    }
}

fun View.rotateAnimAndGone(angle :Float ,drawableEnd: Int, duration : Long, context: Context) {
    animate().apply {
        setDuration(duration)
        rotation(angle)
    }.withEndAction {
        (this as ImageView).setImageDrawable(ContextCompat.getDrawable(context,drawableEnd))
        visibility = View.GONE
    }
}
fun View.rotateAnim(angle :Float, duration : Long, hide : Boolean) {
    animate().apply {
        setDuration(duration)
        rotation(angle)
    }.withEndAction {
        visibility = isGone(hide)
    }
}


fun View.makeVisibleWithAnimation(context: Context, animation : Int) {
    visibility = View.VISIBLE
    setAnimation(AnimationUtils.loadAnimation(context, animation))
}



fun View.makeGoneWithAnimation(context: Context, animation : Int) {
    visibility = View.GONE
    setAnimation(AnimationUtils.loadAnimation(context, animation))
}




fun View.makeGone() {
    visibility = View.GONE
}
fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}




package com.aelsi2.practice12

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

private fun renderDrawableToBitmap(drawable: Drawable) : Bitmap {
    val bitmap: Bitmap =
        if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            )
        }
        else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

class RoundedImageButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageButton(context, attrs) {


    override fun setImageDrawable(drawable: Drawable?) {

        if (drawable is RoundedBitmapDrawable){
            super.setImageDrawable(drawable)
            return
        }
        if (drawable == null) return
        val bitmap : Bitmap =
            if (drawable is BitmapDrawable) {
                drawable.bitmap
            }
            else {
                renderDrawableToBitmap(drawable)
            }
        val drawableNew = RoundedBitmapDrawableFactory.create(resources, bitmap)
        drawableNew.isCircular = true
        super.setImageDrawable(drawableNew)
    }
}
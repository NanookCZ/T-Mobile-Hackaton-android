package com.rudolfhladik.tmobile.prototype.extendedViews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 *
 * Created by rd on 03/12/2016.
 */
class RoundedImageView(context: Context) : ImageView(context) {


    override fun onDraw(canvas: Canvas?) {
        var mDrawable: Drawable = drawable
        if (mDrawable == null) {
            return
        }
        if (width == 0 || height == 0) {
            return
        }
        val b = mDrawable as BitmapDrawable
        val bitmap = b.bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val w = width
        val h = height
        val roundBitmap = getCroppedBitmap(bitmap, w)
        canvas?.drawBitmap(roundBitmap, Rect(0, 0, 0, 0), Rect(0, 0, 0, 0), null)
    }

    fun getCroppedBitmap(bmp: Bitmap, radius: Int): Bitmap {

        val sbmp: Bitmap

        if (bmp.width != radius || bmp.height != radius) {
            val smallest: Float = Math.min(bmp.width, bmp.height).toFloat()
            var factor: Float = smallest / radius
            sbmp = Bitmap.createScaledBitmap(bmp, bmp.width / radius, bmp.height / radius, false)

        } else {
            sbmp = bmp
        }
        val output = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = "#BAB399"
        val paint = Paint()
        val rect = Rect(0, 0, radius, radius)
        with(paint) {
            isAntiAlias = true
            isDither = true
            setColor(Color.parseColor(color))
            setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        }
        with(canvas) {
            drawARGB(0, 0, 0, 0)
            drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f,
                    radius / 2 + 0.1f, paint)
            drawBitmap(sbmp, rect, rect, paint)
        }
        return output
    }


}

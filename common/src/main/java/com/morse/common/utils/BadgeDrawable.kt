package com.expertapps.connect.utils

import com.morse.common.R
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat


class BadgeDrawable(context: Context , var currentCount : String) : Drawable() {
    private val mBadgePaint: Paint
    private val mBadgePaint1: Paint
    private val mTextPaint: Paint
    private val mTxtRect: Rect = Rect()
    private var mCount = ""
    private var mWillDraw = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun draw(canvas: Canvas) {
        if (!mWillDraw) {
            return
        }
        val bounds: Rect = bounds
        val width: Float = bounds.right.toFloat() - bounds.left.toFloat()
        val height: Float = bounds.bottom.toFloat() - bounds.top.toFloat()

        val radius = Math.max(width, height) / 2 / 2
        val centerX = width - radius - 1 + 5
        val centerY = radius - 5
        canvas.drawRoundRect(centerX - 25,  centerY - 25 ,centerX + 25 , centerY + 25, 10f , 10f ,mBadgePaint1)

        mTextPaint.getTextBounds(mCount, 0, mCount.length, mTxtRect)
        val textHeight: Float = mTxtRect.bottom.toFloat() - mTxtRect.top.toFloat()
        val textY = centerY + textHeight / 2f
        if (currentCount.length > 1) canvas.drawText(
            currentCount,
            centerX,
            textY,
            mTextPaint
        ) else canvas.drawText(mCount, centerX, textY, mTextPaint)
    }

    /*
    Sets the count (i.e notifications) to display.
     */
    fun setCount(count: String) {
        mCount = count

        // Only draw a badge if there are notifications.
        var condition = count.toInt()
        mWillDraw = condition > 0
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        // do nothing
    }

    override fun setColorFilter(cf: ColorFilter?) {
        // do nothing
    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    init {
        val mTextSize: Float = context.resources.getDimension(R.dimen._20sdp)
        mBadgePaint = Paint()
        mBadgePaint.color = Color.RED
        mBadgePaint.isAntiAlias = true
        mBadgePaint.style = Paint.Style.FILL
        mBadgePaint1 = Paint()
        mBadgePaint1.color = ContextCompat.getColor(
            context.applicationContext,
            R.color.red_FF0000
        )
        mBadgePaint1.isAntiAlias = true
        mBadgePaint1.style = Paint.Style.FILL
        mTextPaint = Paint()
        mTextPaint.color = Color.WHITE
        mTextPaint.typeface = Typeface.DEFAULT_BOLD
        mTextPaint.textSize = mTextSize
        mTextPaint.isAntiAlias = true
        mTextPaint.textAlign = Paint.Align.CENTER
    }
}
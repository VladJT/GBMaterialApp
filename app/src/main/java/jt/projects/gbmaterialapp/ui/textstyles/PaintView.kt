package jt.projects.gbmaterialapp.ui.textstyles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class PaintView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    val cellSize = 50


    override fun onDraw(canvas: Canvas) {
        val mPaint = Paint()
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 1f

        for (y in 0..height step cellSize) {
            canvas.drawLine(0f, y.toFloat(), width.toFloat(), y.toFloat(), mPaint)
        }

        for (x in 0..width step cellSize) {
            canvas.drawLine(x.toFloat(), 0f, x.toFloat(), height.toFloat(), mPaint)
        }

        // circle
        mPaint.isAntiAlias = true
        mPaint.color = Color.MAGENTA
        for (i in 1..10) {
            var x = cellSize * i * 2
            canvas.drawCircle(x.toFloat(), x.toFloat(), cellSize.toFloat(), mPaint)
        }

        //text
        mPaint.textSize = 130F
        canvas.drawText("Hello", 300f, 200f, mPaint)

        //rect
        val rect = RectF(700f, 320f, 950f,420f)
        mPaint.color = Color.GREEN
        canvas.drawRect(rect, mPaint)

        // дуга
        mPaint.color = Color.BLACK
        val path = Path().apply {addArc(rect ,180f,180f)}
        canvas.drawArc(rect, 180f,180f,false,mPaint)
        mPaint.textSize = 50f
        canvas.drawTextOnPath("Android", path,0f,0f,mPaint)

    }
}
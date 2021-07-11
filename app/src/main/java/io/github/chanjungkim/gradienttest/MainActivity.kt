package io.github.chanjungkim.gradienttest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val tvGradient by lazy {
        findViewById<TextView>(R.id.tv_gradient)
    }
    val ivGradient by lazy {
        findViewById<ImageView>(R.id.iv_gradient)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gd = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(-0xff2244, -0x00ff00, -0x2544ff, -0x232321, -0xff5599)
        )

        gd.cornerRadius = 0f
        tvGradient.background = gd

        try{
            tvGradient.postDelayed({
                val bitmap = Bitmap.createBitmap(tvGradient.measuredWidth, tvGradient.measuredHeight, Bitmap.Config.RGB_565)
                val canvas = Canvas(bitmap)
                val bgDrawable = tvGradient.background
                if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
                tvGradient.draw(canvas)

                tvGradient.postDelayed({
                    ivGradient.setImageBitmap(bitmap)
                    tvGradient.text = "(0, 0): ${Color.valueOf(bitmap.getPixel(0,0))}\n(0, 100) ${Color.valueOf(bitmap.getPixel(0,100))}\n(0, 200) ${Color.valueOf(bitmap.getPixel(0,200))}"
                }, 5000L)
            }, 2000L)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
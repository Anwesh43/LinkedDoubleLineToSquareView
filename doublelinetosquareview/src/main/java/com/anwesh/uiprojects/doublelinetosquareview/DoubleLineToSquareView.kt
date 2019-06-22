package com.anwesh.uiprojects.doublelinetosquareview

/**
 * Created by anweshmishra on 22/06/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#009688")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawRotLine(i : Int, size : Float, scale : Float, paint : Paint) {
    save()
    translate((size / 2) * (1f - 2 * i), 0f)
    drawLine(0f, 0f, 0f, size / 2, paint)
    save()
    translate(0f, size / 2)
    rotate(90f * (1f - 2 * i) * scale.divideScale(i, lines / 2))
    drawLine(0f, 0f, 0f, size / 2, paint)
    restore()
    restore()
}

fun Canvas.drawDoubleRotLine(i : Int, size : Float, scale : Float, paint : Paint) {
    val sci : Float = scale.divideScale(i, lines / 2)
    for (j in 0..(lines / 2 - 1)) {
        save()
        drawRotLine(j, size, sci, paint)
        restore()
    }
}

fun Canvas.drawDLSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.strokeCap = Paint.Cap.ROUND
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    save()
    translate(w / 2, gap * (i + 1))
    scale(sc2, sc2)
    for (j in 0..(lines / 2 - 1)) {
        drawDoubleRotLine(j, size, sc1, paint)
    }
    restore()
}

class DoubleLineToSquareView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}

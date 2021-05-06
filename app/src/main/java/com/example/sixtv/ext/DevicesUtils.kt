package com.example.sixtv.ext

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

@Suppress("DEPRECATION")
val Context.screenSizePoint: Point
    get() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }
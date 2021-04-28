package com.example.sixtv.ext

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

val Number.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).roundToInt()
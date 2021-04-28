package com.example.sixtv.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Toast 相关扩展函数汇总
 *
 * @author DQDana For Olivia
 * @since 4/13/21 7:59 PM
 */

fun FragmentActivity.toast(msg: String?) {
    toast(this.applicationContext, msg)
}

fun Fragment.toast(msg: String?) {
    toast(requireContext().applicationContext, msg)
}

fun View.toast(msg: String?) {
    toast(context.applicationContext, msg)
}

private fun toast(context: Context, msg: String?) {
    Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT).show()
}

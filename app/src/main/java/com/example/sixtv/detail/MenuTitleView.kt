package com.example.sixtv.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sixtv.databinding.ViewMenuTitleBinding
import com.example.sixtv.network.Tv

class MenuTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewMenuTitleBinding.inflate(LayoutInflater.from(getContext()), this, true)

    fun bindData(item: Tv?) {
        item ?: return
        binding.tvTitle.text = item.tvName
    }
}
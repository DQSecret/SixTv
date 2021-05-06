package com.example.sixtv.detail

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sixtv.databinding.ViewMenuItemBinding
import com.example.sixtv.network.Menu

class MenuItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewMenuItemBinding.inflate(LayoutInflater.from(getContext()), this, true)

    @SuppressLint("SetTextI18n")
    fun bindData(item: Menu?) {
        if (item == null || item.isBlank()) return

        item.getBgColor()?.let { binding.root.setBackgroundColor(it) } ?: return

        binding.tvNo.text = "${item.no}."
        binding.tvNameZh.text = item.name
        binding.tvNameEn.text = item.enName
        if (item.shortContent.isNotBlank()) {
            binding.tvShortContent.text = item.shortContent
            binding.tvShortContent.visibility = View.VISIBLE
        } else {
            binding.tvShortContent.visibility = View.GONE
        }
        if (item.isNew == "1") {
            binding.ivLatestTips.visibility = View.VISIBLE
        } else {
            binding.ivLatestTips.visibility = View.GONE
        }
        if (item.winning.isNotBlank()) {
            binding.tvWinning.text = item.winning.split(" ").joinToString("\n")
            binding.tvWinning.visibility = View.VISIBLE
        } else {
            binding.tvWinning.visibility = View.GONE
        }

        binding.tvAlcohol.text = "abv.${item.abv}%"
        binding.tvSingleCup.text = "¥ ${item.single}"
        binding.tvALiter.text = "¥ ${item.l1}"
        binding.tvTwoLitres.text = "¥ ${item.l2}"
        binding.tvCans.text = "¥ ${item.cans}"
    }
}
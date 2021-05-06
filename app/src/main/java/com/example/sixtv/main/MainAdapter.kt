package com.example.sixtv.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sixtv.databinding.ItemSelectElementBinding
import com.example.sixtv.detail.MenuDetailsActivity
import com.example.sixtv.ext.toast
import com.example.sixtv.network.Tv

class MainAdapter : RecyclerView.Adapter<MainAdapter.VH>() {

    private val data = mutableListOf<Tv>()

    fun setData(newData: List<Tv>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH.getInstance(parent)
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(item = data[position])

    class VH(
        private val binding: ItemSelectElementBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun getInstance(parent: ViewGroup): VH {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemSelectElementBinding.inflate(inflater, parent, false)
                return VH(binding)
            }
        }

        fun bind(item: Tv?) {
            item ?: return
            binding.btnElements.apply {
                text = item.name
                setOnClickListener {
                    // toast("点击了: $item")
                    MenuDetailsActivity.start(it.context, item.id)
                }
            }
        }
    }
}
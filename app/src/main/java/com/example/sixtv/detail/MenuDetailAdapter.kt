package com.example.sixtv.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sixtv.databinding.ItemMenuDetailElementBinding
import com.example.sixtv.network.Menu

class MenuDetailAdapter : RecyclerView.Adapter<MenuDetailAdapter.VH>() {

    private val data = mutableListOf<Menu>()

    fun setData(newData: List<Menu>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH.getInstance(parent)
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(item = data[position])

    class VH(
        private val binding: ItemMenuDetailElementBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun getInstance(parent: ViewGroup): VH {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemMenuDetailElementBinding.inflate(inflater, parent, false)
                return VH(binding)
            }
        }

        fun bind(item: Menu?) {
            binding.viewMenuItem.bindData(item)
        }
    }
}
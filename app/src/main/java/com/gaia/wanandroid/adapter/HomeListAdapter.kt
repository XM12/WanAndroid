package com.gaia.wanandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gaia.wanandroid.bean.DatasBean
import com.gaia.wanandroid.databinding.ItemHomeBinding

class HomeListAdapter(private val context: Context, private val data: MutableList<DatasBean>) :
    RecyclerView.Adapter<HomeListAdapter.VH>() {

    fun notifyData(data: MutableList<DatasBean>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getData(): MutableList<DatasBean> {
        return data
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemHomeBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class VH(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bean: DatasBean) {
            binding.item = bean
        }
    }
}
package com.example.kemockui.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kemockui.databinding.ItemRvMainCommonBinding
import com.example.kemockui.entities.ItemHomework
import com.example.kemockui.entities.ItemLesson

class RvAdapterHW(
    //private val onItemClickListener: (ItemRv) -> Unit
) : RecyclerView.Adapter<RvAdapterHW.ViewHolder>() {
    var adapterList: List<ItemHomework> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRvMainCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size

    inner class ViewHolder(private val viewBinding: ItemRvMainCommonBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(adapterItemView: ItemHomework) {
            viewBinding.tvTitle.text = adapterItemView.title
            viewBinding.tvLeft.text = adapterItemView.beginTime


        }
    }
}
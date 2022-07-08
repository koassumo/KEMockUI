package com.example.kemockui.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kemockui.databinding.ItemRvMainLessonBinding
import com.example.kemockui.entities.ItemLesson

class RvAdapterLesson(
    //private val onItemClickListener: (ItemRv) -> Unit
) : RecyclerView.Adapter<RvAdapterLesson.ViewHolder>() {
    var adapterList: List<ItemLesson> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRvMainLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size

    inner class ViewHolder(private val viewBinding: ItemRvMainLessonBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(adapterItemView: ItemLesson) {
            viewBinding.titleClasses.text = adapterItemView.title
            if (adapterItemView.isGreenAdditionalLesson) {
                viewBinding.onlineText.text = "ONLINE"
                viewBinding.online.setBackgroundColor(Color.BLUE)
            }

        }
    }
}
package com.example.curlapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.curlapp.NasaAdapter.Const.APOD
import com.example.curlapp.NasaAdapter.Const.MARTIAN
import com.example.curlapp.databinding.ApodItemBinding
import com.example.curlapp.databinding.MartianItemBinding
import com.squareup.picasso.Picasso

class NasaAdapter(private var items: ArrayList<NasaAPI>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ApodViewHolder(private val apodItem: ApodItemBinding):
        RecyclerView.ViewHolder(apodItem.root) {
            fun bind(item: NasaAPI) {
                Glide.with(apodItem.imageIv.context)
                    .load(item.image)
                    .error(R.drawable.ic_launcher_background)
                    .into(apodItem.imageIv)
                apodItem.dateTv.text = item.date
            }
        }

    inner class MartianViewHolder(private val martianItem: MartianItemBinding):
        RecyclerView.ViewHolder(martianItem.root) {
        fun bind(item: NasaAPI) {
            Picasso.get()
                .load(item.image)
                .error(R.drawable.ic_launcher_background)
                .into(martianItem.imageIv)
            martianItem.dateTv.text = item.date
            martianItem.textTv.text = item.text
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == APOD) {
            val view = ApodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ApodViewHolder(view)
        } else {
            val view = MartianItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MartianViewHolder(view)
        }
    }

    private object Const{
        const val APOD = 1
        const val MARTIAN = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].text != null) MARTIAN else APOD
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == APOD) {
            (holder as ApodViewHolder).bind(items[position])
        } else {
            (holder as MartianViewHolder).bind(items[position])
        }
    }

    fun submitList(newList: ArrayList<NasaAPI>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(items, newList))
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    class MyDiffCallback (
        private val oldList: ArrayList<NasaAPI>,
        private val newList: ArrayList<NasaAPI>
    ): DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].image == newList[newItemPosition].image
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return when {
                oldList[oldItemPosition].date == newList[newItemPosition].date -> true
                oldList[oldItemPosition].text == newList[newItemPosition].text -> true
                oldList[oldItemPosition].title == newList[newItemPosition].title -> true
                oldList[oldItemPosition].image == newList[newItemPosition].image -> true
                else -> false
            }
        }
        override fun getOldListSize(): Int {
            return oldList.size
        }
        override fun getNewListSize(): Int {
            return newList.size
        }
    }

}
package com.example.curlapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.curlapp.NasaAdapter.Const.APOD
import com.example.curlapp.NasaAdapter.Const.MARTIAN
import com.example.curlapp.databinding.ApodItemBinding
import com.example.curlapp.databinding.MartianItemBinding

class NasaAdapter(private var items: ArrayList<NasaAPI>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ApodViewHolder(private val apodItem: ApodItemBinding):
        RecyclerView.ViewHolder(apodItem.root) {
            fun bind(item: NasaAPI) {
//                apodItem.imageIv.setImageResource(TODO())
                apodItem.dateTv.text = item.date
            }
        }

    inner class MartianViewHolder(private val martianItem: MartianItemBinding):
        RecyclerView.ViewHolder(martianItem.root) {
        fun bind(item: NasaAPI) {
//            martianItem.imageIv.setImageResource(TODO())
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

}
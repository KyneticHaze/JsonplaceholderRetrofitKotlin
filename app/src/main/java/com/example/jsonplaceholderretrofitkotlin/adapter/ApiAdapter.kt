package com.example.jsonplaceholderretrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderretrofitkotlin.databinding.RecyclerItemBinding
import com.example.jsonplaceholderretrofitkotlin.model.ApiModel

class ApiAdapter(private val apiList: ArrayList<ApiModel>, private val listener: Listener) : RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {

    private var colors : Array<String> = arrayOf("#c9eb34", "#902e99", "#8c2651", "#f59c76", "#74c7d6","#83e6a7", "#fa464c", "#9d7ceb")

    interface Listener {
        fun onItemClick(apiModel: ApiModel)
    }

    inner class ApiViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(apiModel : ApiModel, color: Array<String> ,position: Int, listener: Listener) {

            itemView.setOnClickListener {
                listener.onItemClick(apiModel)
            }

            itemView.setBackgroundColor(Color.parseColor(color[position % 8]))
            binding.currencyText.text = apiModel.currency
            binding.priceText.text = apiModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApiViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return apiList.size
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        holder.bind(apiList[position], colors, position, listener)
    }
}
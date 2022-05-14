package com.jomibusa.challengemeli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.data.model.Attributes
import com.jomibusa.challengemeli.data.model.Results
import com.jomibusa.challengemeli.databinding.ItemDetailListBinding

class ItemDetailAdapter : RecyclerView.Adapter<ItemDetailAdapter.DetailViewHolder>() {

    private var attributeList: List<Attributes> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding =
            ItemDetailListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val attribute = attributeList[position]
        holder.bind(attribute)
    }

    override fun getItemCount() = attributeList.size

    fun submitList(attributeList: List<Attributes>) {
        this.attributeList = attributeList
    }

    inner class DetailViewHolder(
        private val binding: ItemDetailListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var attribute: Attributes? = null

        fun bind(attribute: Attributes) {

            this.attribute = attribute

            binding.apply {
                textViewDetailName.text = attribute.name
                textViewDetailValue.text = attribute.valueName
            }
        }
    }

}
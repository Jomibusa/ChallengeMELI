package com.jomibusa.challengemeli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.R
import com.jomibusa.challengemeli.data.model.Results
import com.jomibusa.challengemeli.databinding.ItemListBinding
import com.jomibusa.challengemeli.util.Util
import com.squareup.picasso.Picasso
import java.util.*

/**
 * @author Jomibusa
 */

class ItemAdapter(private val onClick: (Results) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var itemList: List<Results> = listOf()
    private var itemListFilter: List<Results> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemListFilter[position]
        holder.bind(item)
    }

    override fun getItemCount() = itemListFilter.size

    fun submitList(itemList: List<Results>) {
        this.itemList = itemList
        this.itemListFilter = itemList
    }

    inner class ItemViewHolder(
        private val binding: ItemListBinding,
        private val onClick: (Results) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var item: Results? = null

        init {
            binding.cardViewItem.setOnClickListener { item?.let { onClick(it) } }
        }

        fun bind(item: Results) {

            this.item = item

            binding.apply {
                textViewNameItem.text = item.title
                textViewPrice.text = Util.parseToCurrencyFormat(item.price)
                val quantity =
                    "${item.quantity} ${binding.root.context.getString(R.string.text_items_available)}"
                textViewQuantity.text = quantity
                processCondition(item)
                if (item.mercadoPago) {
                    textViewMercadoPago.visibility = View.VISIBLE
                }
                if (item.imageItem.isNotEmpty()) {
                    Picasso.get().load(Util.replaceUrl(item.imageItem))
                        .placeholder(R.drawable.default_item).into(imageViewItem)
                }
            }
        }

        /**
         * Función utilizada para setear la información relacionada en cuanto a la condición del
         * producto
         */
        private fun processCondition(item: Results) {
            if (item.condition != null) {
                binding.textViewCondition.text = item.condition.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            } else {
                binding.textViewCondition.visibility = View.GONE
            }
        }
    }

}
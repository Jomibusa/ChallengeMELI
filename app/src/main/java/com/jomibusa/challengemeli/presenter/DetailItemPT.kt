package com.jomibusa.challengemeli.presenter

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.adapter.ItemDetailAdapter
import com.jomibusa.challengemeli.data.model.Attributes
import com.jomibusa.challengemeli.interfaces.IDetailItemCT

class DetailItemPT : IDetailItemCT.Presenter {

    private val TAG = DetailItemPT::class.java.simpleName

    private lateinit var recyclerViewAttributes: RecyclerView
    private lateinit var detailAdapter: ItemDetailAdapter

    override fun start(recyclerView: RecyclerView, listAttributes: List<Attributes>) {
        this.recyclerViewAttributes = recyclerView
        setAdapter(listAttributes)
    }

    private fun setAdapter(listAttributes: List<Attributes>) {
        detailAdapter = ItemDetailAdapter()
        detailAdapter.submitList(listAttributes)
        recyclerViewAttributes.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = detailAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }

}
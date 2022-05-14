package com.jomibusa.challengemeli.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.adapter.ItemAdapter
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.data.network.RetrofitManager
import com.jomibusa.challengemeli.interfaces.IListItemCT

class ListItemPT(private val view: IListItemCT.View) : IListItemCT.Presenter {

    private lateinit var retrofitManager: RetrofitManager

    private lateinit var recyclerViewItems: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    private lateinit var context: Context

    override fun start(context: Context, recyclerView: RecyclerView, itemName: String) {
        this.recyclerViewItems = recyclerView
        this.context = context
        retrofitManager = RetrofitManager()

        getListItems(itemName)
    }

    private fun getListItems(itemName: String) {
        retrofitManager.getListItems(itemName) {
            if (it != null && it.results.isNotEmpty()) {
                setAdapter(it)
                view.showLoading(false)
                view.showNotData(false)
            } else {
                view.showLoading(false)
                view.showNotData(true)
            }
        }
    }

    private fun setAdapter(item: Item) {
        itemAdapter = ItemAdapter { result ->
            view.navigateToDetailItem(result)
        }
        itemAdapter.submitList(item.results)
        recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = itemAdapter
        }
        view.showListItems(true)
    }

    override fun doFilter(search: String?) {
        itemAdapter.filter.filter(search)
        if (itemAdapter.itemCount <= 0) {
            view.showListItems(false)
            view.showNotData(true)
        } else {
            view.showListItems(true)
            view.showNotData(false)
        }
    }
}
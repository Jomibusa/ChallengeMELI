package com.jomibusa.challengemeli.interfaces

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.data.model.Results

interface IListItemCT {

    interface Presenter {

        fun start(context: Context, recyclerView: RecyclerView, itemName: String)

        fun doFilter(search: String?)

    }


    interface View {

        fun showListItems(show: Boolean)

        fun showNotData(show: Boolean)

        fun showLoading(show: Boolean)

        fun navigateToDetailItem(result: Results)

    }

}
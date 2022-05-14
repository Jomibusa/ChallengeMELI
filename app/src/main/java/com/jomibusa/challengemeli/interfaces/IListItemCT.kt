package com.jomibusa.challengemeli.interfaces

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.data.model.Results

interface IListItemCT {

    interface Presenter {

        fun start(recyclerView: RecyclerView, itemName: String)

    }


    interface View {

        fun showListItems(show: Boolean)

        fun showNotData(show: Boolean)

        fun showLoading(show: Boolean)

        fun navigateToDetailItem(result: Results)

    }

}
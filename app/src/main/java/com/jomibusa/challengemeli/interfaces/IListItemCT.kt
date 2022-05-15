package com.jomibusa.challengemeli.interfaces

import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.data.model.Results

/**
 * @author Jomibusa
 */

interface IListItemCT {

    interface Presenter {

        fun start(itemName: String)

        fun cancelRequest()

    }


    interface View {

        fun setAdapter(item: Item)

        fun showListItems(show: Boolean)

        fun showInfoData(show: Boolean, message: Int? = null)

        fun showLoading(show: Boolean)

        fun navigateToDetailItem(result: Results)

    }

}
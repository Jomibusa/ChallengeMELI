package com.jomibusa.challengemeli.presenter

import com.jomibusa.challengemeli.R
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.data.network.RetrofitManager
import com.jomibusa.challengemeli.interfaces.IListItemCT

class ListItemPT(private var view: IListItemCT.View?) : IListItemCT.Presenter,
    RetrofitManager.IOnDetailFetched {

    private lateinit var retrofitManager: RetrofitManager

    override fun start(itemName: String) {
        retrofitManager = RetrofitManager()
        getListItems(itemName)
    }

    private fun getListItems(itemName: String) {
        retrofitManager.getListItems(itemName, this)
    }

    override fun onSuccess(item: Item) {
        view?.setAdapter(item)
        view?.showLoading(false)
        view?.showInfoData(false)
    }

    override fun onFailure() {
        view?.showLoading(false)
        view?.showInfoData(true, R.string.text_no_data_error)
    }

    override fun noResults() {
        view?.showLoading(false)
        view?.showInfoData(true, R.string.text_no_data_available)
    }

    override fun cancelRequest() {
        view = null
        retrofitManager.cancelRequest()
    }
}
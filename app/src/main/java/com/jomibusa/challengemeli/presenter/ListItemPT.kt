package com.jomibusa.challengemeli.presenter

import com.jomibusa.challengemeli.R
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.data.network.RetrofitManager
import com.jomibusa.challengemeli.interfaces.IListItemCT

/**
 * @author Jomibusa
 */

class ListItemPT(private var view: IListItemCT.View?) : IListItemCT.Presenter,
    RetrofitManager.IOnDetailFetched {

    private lateinit var retrofitManager: RetrofitManager

    /**
     * Función que se encarga de instanciar Retrofit, así como de iniciar el consumo del servicio
     * de acuerdo al item a consultar
     * @param itemName Es el nombre del item a buscar con el servicio
     */
    override fun start(itemName: String) {
        retrofitManager = RetrofitManager()
        getListItems(itemName)
    }

    /**
     * Función que utiliza Retrofit para realizar el consumo de acuerdo al item a consultar
     * @param itemName Es el nombre del item a buscar con el servicio
     */
    private fun getListItems(itemName: String) {
        retrofitManager.getListItems(itemName, this)
    }

    /**
     * Función que me indica que la consulta al servicio fue exitosa y es momento de avisar a la
     * vista para que esta muestre el listado
     * @param item El modelo obtenido por el consumo, del nombre del item consultado
     */
    override fun onSuccess(item: Item) {
        view?.setAdapter(item)
        view?.showLoading(false)
        view?.showInfoData(false)
    }

    /**
     * Función que me indica que hubo un error al momento de querer intentar realizar el consumo,
     * y así de esta forma se le avisa al usuario lo sucedido
     */
    override fun onFailure() {
        view?.showLoading(false)
        view?.showInfoData(true, R.string.text_no_data_error)
    }

    /**
     * Función que me indica que aunque el consumo fue exitoso, no se obtuvo ningún resultado
     * con el nombnre del item consultado, de esta forma se le avisa al usuario lo sucedido
     */
    override fun noResults() {
        view?.showLoading(false)
        view?.showInfoData(true, R.string.text_no_data_available)
    }

    /**
     * Función que cancela el consumo que se esté realizando en ese momento con Retrofit, así como
     * de avisar que la instancia de la vista ya no existe y ahora es nula
     */
    override fun cancelRequest() {
        view = null
        retrofitManager.cancelRequest()
    }
}
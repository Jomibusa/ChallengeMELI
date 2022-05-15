package com.jomibusa.challengemeli.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jomibusa.challengemeli.R
import com.jomibusa.challengemeli.adapter.ItemAdapter
import com.jomibusa.challengemeli.base.BaseFragment
import com.jomibusa.challengemeli.data.model.Item
import com.jomibusa.challengemeli.data.model.Results
import com.jomibusa.challengemeli.databinding.FragmentListItemsBinding
import com.jomibusa.challengemeli.interfaces.IListItemCT
import com.jomibusa.challengemeli.presenter.ListItemPT

/**
 * @author Jomibusa
 */

class ListItemsFragment : BaseFragment(), IListItemCT.View {

    private var _binding: FragmentListItemsBinding? = null
    private val binding get() = _binding!!

    private val args: ListItemsFragmentArgs by navArgs()

    private lateinit var presenter: IListItemCT.Presenter

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListItemsBinding.inflate(inflater, container, false)

        setToolBar()

        presenter = ListItemPT(this@ListItemsFragment)

        presenter.start(args.nameItem)

        binding.materialButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    /**
     * Función utilizada para configurar el custom Toolbar en la pantalla y dar las respectivas
     * funcionalidades necesarias
     */
    private fun setToolBar() {
        binding.apply {
            containerToolbar.toolbar.apply {
                title = getString(R.string.text_title_toolbar_results)

                navigationIcon?.mutate()?.let {
                    it.setTint(requireContext().getColor(R.color.meli_blue))
                    containerToolbar.toolbar.navigationIcon = it
                }

                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }

    /**
     * Función utilizada para realizar la respectiva configuración del adapter utilizado en este
     * fragment
     * @param item Es el modelo obtenido del consumo y el cual se utilizará para pintar la
     * información que será presentada al usuario
     */
    override fun setAdapter(item: Item) {
        itemAdapter = ItemAdapter { result ->
            navigateToDetailItem(result)
        }
        itemAdapter.submitList(item.results)
        binding.recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = itemAdapter
        }
        showListItems(true)
    }

    /**
     * Función que se encarga de hacer visible para el usuario la lista de items encontrados
     * por el consumo de acuerdo a la palabra utilizada para su búsqueda
     * @param show Me indica si debo o no volver a hacer visible esa vista
     */
    override fun showListItems(show: Boolean) {
        if (show) {
            binding.recyclerViewItems.visibility = View.VISIBLE
        } else {
            binding.recyclerViewItems.visibility = View.GONE
        }
    }

    /**
     * Función que se encarga de hacer visible o no para el usuario un mensaje informativo sobre
     * cosas básicas como problemas con la conexión, etc
     * @param show Me indica si debo o no volver a hacer visible esa vista
     * @param message Es el mensaje que será utilizado para dar aviso al usuario sobre alguna
     * información
     */
    override fun showInfoData(show: Boolean, message: Int?) {
        if (show) {
            message?.let {
                binding.textViewInfo.text = requireContext().getString(it)
            }
            binding.textViewInfo.visibility = View.VISIBLE
            binding.materialButtonBack.visibility = View.VISIBLE
        } else {
            binding.textViewInfo.visibility = View.GONE
            binding.materialButtonBack.visibility = View.GONE
        }
    }

    /**
     * Función que se encarga de hacer visible o no para el usuario una pantalla de carga, el cual
     * me indica como usuario que en ese momento se está ejecutando algo.
     * @param show Me indica si debo o no volver a hacer visible esa vista
     * información
     */
    override fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    /**
     * Función que me permite navegar a un fragment que tendrá toda la información más detallada
     * del item de acuerdo a consumos anteriores
     * @param result Es el resultado escogido, para que de esta forma toda su información
     * pueda ser enviada al fragment que mostrará al usuario más a detalle su información
     */
    override fun navigateToDetailItem(result: Results) {
        findNavController().navigate(
            ListItemsFragmentDirections.actionListProductsFragmentToDetailItemFragment(
                result
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }

}
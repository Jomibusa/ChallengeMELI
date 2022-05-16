package com.jomibusa.challengemeli.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jomibusa.challengemeli.R
import com.jomibusa.challengemeli.adapter.ItemDetailAdapter
import com.jomibusa.challengemeli.base.BaseFragment
import com.jomibusa.challengemeli.data.model.Attributes
import com.jomibusa.challengemeli.data.model.Results
import com.jomibusa.challengemeli.databinding.FragmentDetailItemBinding
import com.jomibusa.challengemeli.util.Util
import com.squareup.picasso.Picasso
import java.util.*

/**
 * @author Jomibusa
 */

class DetailItemFragment : BaseFragment() {

    private var _binding: FragmentDetailItemBinding? = null
    private val binding get() = _binding!!

    private val args: DetailItemFragmentArgs by navArgs()

    private lateinit var detailAdapter: ItemDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailItemBinding.inflate(inflater, container, false)

        setToolBar()

        setInitData()

        args.item.attributes?.let {
            setAdapter(it)
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
                title = getString(R.string.text_title_toolbar_detail)

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
     * Función utilizada para realizar el seteo de información previa que ya se obtiene desde el
     * fragment anterior y que llega a este por medio de los SafeArgs
     */
    private fun setInitData() {
        binding.apply {
            includeDetail.textViewNameItem.text = args.item.title
            includeDetail.textViewPrice.text = Util.parseToCurrencyFormat(args.item.price)
            val quantity =
                "${args.item.quantity} ${binding.root.context.getString(R.string.text_items_available)}"
            includeDetail.textViewQuantity.text = quantity
            processCondition(args.item)
            if (args.item.mercadoPago) {
                includeDetail.textViewMercadoPago.visibility = View.VISIBLE
            }
            if (args.item.imageItem.isNotEmpty()) {
                Picasso.get().load(Util.replaceUrl(args.item.imageItem))
                    .placeholder(R.drawable.default_item).into(includeDetail.imageViewItem)
            }
        }
    }

    /**
     * Función utilizada para setear la información relacionada en cuanto a la condición del
     * producto
     */
    private fun processCondition(item: Results) {
        if (item.condition != null) {
            binding.includeDetail.textViewCondition.text = item.condition.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        } else {
            binding.includeDetail.textViewCondition.visibility = View.GONE
        }
    }

    /**
     * Función utilizada para realizar la respectiva configuración del adapter utilizado en este
     * fragment
     * @param listAttributes Lista de atributos que se utilizaran para pintar la información en la
     * vista del detalle del item seleccionado
     */
    private fun setAdapter(listAttributes: List<Attributes>) {
        detailAdapter = ItemDetailAdapter()
        detailAdapter.submitList(listAttributes)
        val layout = LinearLayoutManager(requireContext())
        binding.recyclerViewAttributes.apply {
            layoutManager = layout
            isNestedScrollingEnabled = false
            adapter = detailAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    layout.orientation
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
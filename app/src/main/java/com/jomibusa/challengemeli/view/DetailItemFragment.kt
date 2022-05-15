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
import com.jomibusa.challengemeli.databinding.FragmentDetailItemBinding
import com.jomibusa.challengemeli.util.Util
import com.squareup.picasso.Picasso

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

    private fun setInitData() {
        binding.apply {
            includeDetail.textViewNameItem.text = args.item.title
            includeDetail.textViewPrice.text = Util.parseToCurrencyFormat(args.item.price)
            val quantity =
                "${args.item.quantity} ${binding.root.context.getString(R.string.text_items_available)}"
            includeDetail.textViewQuantity.text = quantity
            if (args.item.mercadoPago) {
                includeDetail.textViewMercadoPago.visibility = View.VISIBLE
            }
            if (args.item.imageItem.isNotEmpty()) {
                Picasso.get().load(Util.replaceUrl(args.item.imageItem))
                    .placeholder(R.drawable.default_item).into(includeDetail.imageViewItem)
            }
        }
    }

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
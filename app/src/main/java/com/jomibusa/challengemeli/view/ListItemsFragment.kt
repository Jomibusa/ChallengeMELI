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

    override fun setAdapter(item: Item) {
        activity?.let {
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
    }

    override fun showListItems(show: Boolean) {
        if (show) {
            binding.recyclerViewItems.visibility = View.VISIBLE
        } else {
            binding.recyclerViewItems.visibility = View.GONE
        }
    }

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

    override fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

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
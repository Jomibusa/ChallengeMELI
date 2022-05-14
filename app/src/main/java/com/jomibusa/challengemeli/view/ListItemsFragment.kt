package com.jomibusa.challengemeli.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.navArgs
import com.jomibusa.challengemeli.base.BaseFragment
import com.jomibusa.challengemeli.data.model.Results
import com.jomibusa.challengemeli.databinding.FragmentListItemsBinding
import com.jomibusa.challengemeli.interfaces.IListItemCT
import com.jomibusa.challengemeli.presenter.ListItemPT

class ListItemsFragment : BaseFragment(), IListItemCT.View, SearchView.OnQueryTextListener {

    private var _binding: FragmentListItemsBinding? = null
    private val binding get() = _binding!!

    private val args: ListItemsFragmentArgs by navArgs()

    private lateinit var presenter: IListItemCT.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListItemsBinding.inflate(inflater, container, false)

        binding.apply {

            presenter = ListItemPT(this@ListItemsFragment)

            presenter.start(requireContext(), recyclerViewItems, args.nameItem)

            binding.searchViewUser.setOnQueryTextListener(this@ListItemsFragment)

            val closeButton =
                binding.searchViewUser.findViewById<View>(androidx.appcompat.R.id.search_close_btn)
            closeButton.setOnClickListener {
                binding.searchViewUser.setQuery("", false)
                showListItems(true)
                showNotData(false)
            }

        }

        return binding.root
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.doFilter(newText)
        return false
    }

    override fun showListItems(show: Boolean) {
        if (show) {
            binding.recyclerViewItems.visibility = View.VISIBLE
        } else {
            binding.recyclerViewItems.visibility = View.GONE
        }
    }

    override fun showNotData(show: Boolean) {
        if (show) {
            binding.textViewNoData.visibility = View.VISIBLE
        } else {
            binding.textViewNoData.visibility = View.GONE
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
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
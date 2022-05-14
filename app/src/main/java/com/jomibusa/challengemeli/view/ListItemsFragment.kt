package com.jomibusa.challengemeli.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jomibusa.challengemeli.base.BaseFragment
import com.jomibusa.challengemeli.databinding.FragmentListItemsBinding

class ListItemsFragment : BaseFragment() {

    private var _binding: FragmentListItemsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListItemsBinding.inflate(inflater, container, false)

        binding.apply {



        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
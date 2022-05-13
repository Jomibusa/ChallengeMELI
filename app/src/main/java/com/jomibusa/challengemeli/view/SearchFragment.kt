package com.jomibusa.challengemeli.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jomibusa.challengemeli.base.BaseFragment
import com.jomibusa.challengemeli.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.apply {

            materialButtonSearch.setOnClickListener {
                val nameProduct = textInputEditTextSearch.text.toString()

                if (nameProduct.isNotEmpty()) {
                    textInputLayoutSearch.error = null
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToListProductsFragment(
                            nameProduct
                        )
                    )
                } else {
                    textInputLayoutSearch.error = "Debes ingresar el producto a consultar"
                }
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.jomibusa.challengemeli.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.jomibusa.challengemeli.R
import com.jomibusa.challengemeli.base.BaseFragment
import com.jomibusa.challengemeli.databinding.FragmentSearchBinding

/**
 * @author Jomibusa
 */

class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.apply {

            /*
             * Se valida que al momento de oprimir el botón de consulta en este ya haya sido
             * escrito algo para proceder a la siguiente vista o de lo contrario dará aviso al
             * usuario
             */
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
                    textInputLayoutSearch.error =
                        requireContext().getString(R.string.text_validation_text_search)
                }
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    showMessageConfirmationCloseApp()
                }
            }
        )
    }

    private fun showMessageConfirmationCloseApp() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.text_title_validate_close_app))
            .setMessage(getString(R.string.text_message_validate_close_app))
            .setPositiveButton(android.R.string.ok) { view, _ ->
                requireActivity().finish()
                view.dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { view, _ ->
                view.dismiss()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
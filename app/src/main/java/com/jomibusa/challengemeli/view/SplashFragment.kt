package com.jomibusa.challengemeli.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jomibusa.challengemeli.base.BaseFragment
import com.jomibusa.challengemeli.databinding.FragmentSplashBinding

/**
 * @author Jomibusa
 */

class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        val timeSplash = 2000L

        /**
         * Se espera un determinado tiempo para luego ser redirigido al fragment de consulta
         */
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSearchFragment())
        }, timeSplash)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.trr1ckster.bnet_test_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.trr1ckster.bnet_test_app.databinding.FragmentDetailsBinding
import com.trr1ckster.bnet_test_app.utils.Utils

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val product = args.product
        product.let {
            binding.titleDetails.text = product.name
            binding.descriptionDetails.text = product.description
            binding.imageProductDetails.load(Utils.BASE_URL + product.image)
            binding.icon.load(Utils.BASE_URL + product.categories?.icon)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
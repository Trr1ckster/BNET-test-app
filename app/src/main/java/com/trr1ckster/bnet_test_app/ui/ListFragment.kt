package com.trr1ckster.bnet_test_app.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.trr1ckster.bnet_test_app.R
import com.trr1ckster.bnet_test_app.ui.adapters.ProductsAdapter
import com.trr1ckster.bnet_test_app.databinding.FragmentListBinding
import com.trr1ckster.bnet_test_app.data.model.ApiModelItem

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding
        get() = _binding!!

    private val productAdapter = ProductsAdapter(object : ProductsAdapter.OnItemClickListener {
        override fun onItemClick(model: ApiModelItem) {
            val bundle = Bundle().apply {
                putSerializable("product", model)
            }
            findNavController().navigate(R.id.action_listFragment_to_detailsFragment, bundle)
        }
    })

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = productAdapter

        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val searchView = menuItem.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.getProductsForSearch(query)
                        getSearchResult()
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        viewModel.getProductsForSearch(newText)
                        getSearchResult()
                        return false
                    }
                })
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    fun getSearchResult() {
        if (view != null) {
            viewModel.productsSearchLiveData.observe(viewLifecycleOwner) {
                productAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
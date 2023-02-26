package com.trr1ckster.bnet_test_app.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trr1ckster.bnet_test_app.data.model.ApiModelItem
import com.trr1ckster.bnet_test_app.data.network.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val _productsLiveData: MutableLiveData<List<ApiModelItem>> = MutableLiveData()
    val productsLiveData: LiveData<List<ApiModelItem>> = _productsLiveData

    private val _productsSearchLiveData: MutableLiveData<List<ApiModelItem>> = MutableLiveData()
    val productsSearchLiveData: LiveData<List<ApiModelItem>> = _productsSearchLiveData

    init {
        getAllProducts()
    }

    private fun getAllProducts() = viewModelScope.launch {
        try {
            _productsLiveData.value = RetrofitClient.productApi.getProducts().body()
        } catch (e: Exception) {
            Log.e("Error","$e")
        }

    }

    fun getProductsForSearch(searchQuery: String?) = viewModelScope.launch {
        try {
            _productsLiveData.value =
                RetrofitClient.productApi.getProductsForSearchQuery(searchQuery).body()
        } catch (e: Exception) {
            Log.e("ErrorSearch","$e")
        }
    }
}
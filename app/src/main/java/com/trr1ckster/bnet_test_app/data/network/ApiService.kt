package com.trr1ckster.bnet_test_app.data.network

import com.trr1ckster.bnet_test_app.data.model.ApiModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/ppp/index")
    suspend fun getProducts(
    ): Response<List<ApiModelItem>>

    @GET("api/ppp/index")
    suspend fun getProductsForSearchQuery(
        @Query("search") searchQuery: String?
    ): Response<List<ApiModelItem>>
}
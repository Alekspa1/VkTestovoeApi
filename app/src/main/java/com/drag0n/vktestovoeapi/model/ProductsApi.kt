package com.drag0n.vktestovoeapi.model

import com.drag0n.vktestovoeapi.Const.LIMIT
import com.drag0n.vktestovoeapi.data.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {
    @GET("products")
    suspend fun getAllProducts(@Query("skip") skip: Int, @Query("limit") limit: Int = LIMIT) : Response<Products>
    @GET("products/search")
    suspend fun getSearchProduct(@Query("q") search: String) : Response<Products>
}
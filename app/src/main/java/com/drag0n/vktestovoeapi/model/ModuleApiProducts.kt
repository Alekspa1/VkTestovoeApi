package com.drag0n.vktestovoeapi.model

import com.drag0n.vktestovoeapi.Const.BASE_URL
import com.drag0n.vktestovoeapi.data.Product
import com.drag0n.vktestovoeapi.view.adapters.ProductsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApiProducts {


    @Provides
    @Singleton
    fun providesRetrofitInstanse(): ProductsApi{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).client(client)
            .build()
        return retrofit.create(ProductsApi::class.java)
    }


}
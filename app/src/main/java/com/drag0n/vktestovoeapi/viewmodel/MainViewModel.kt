package com.drag0n.vktestovoeapi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.drag0n.vktestovoeapi.data.Products
import com.drag0n.vktestovoeapi.model.ProductsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productsApi: ProductsApi,
    private val application: Application
) : AndroidViewModel(application) {

    private val _products = MutableLiveData<Products>()
    val products: LiveData<Products> = _products
    private var counter = 0
    private var limit = 0

  init {
      getProduct()
  }



    fun getProduct() = viewModelScope.launch {
        try {
            val response = productsApi.getAllProducts(counter)
            if (response.isSuccessful && response.body() != null) {
                _products.postValue(response.body())
                limit = response.body()!!.total
            }

        }
         catch (e: Exception) {
             Toast.makeText(application, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
    }
    } // Для первого запуска узнать лимит

    fun getSearchProduct(value: String) = viewModelScope.launch {
        try {
            val response = productsApi.getSearchProduct(value)
            if (response.isSuccessful && response.body() != null) {
                _products.postValue(response.body())
            }

        }
        catch (e: Exception) {
            Toast.makeText(application, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
        }
    } // Для первого запуска узнать лимит

    fun addProduct() = viewModelScope.launch {
        try {
            if (counter < limit-20) {
                counter += 20
                val response = productsApi.getAllProducts(counter)
                if (response.isSuccessful) {
                    _products.postValue(response.body())
                }
            } else Toast.makeText(application, "Больше ничего нет", Toast.LENGTH_SHORT).show()

        }
        catch (e: Exception) {
            Toast.makeText(application, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
        }
    } // + skip
    fun backProduct() = viewModelScope.launch {
        if (counter>0) {
            try {
                counter -= 20
                val response = productsApi.getAllProducts(counter)
                if (response.isSuccessful) {
                    _products.postValue(response.body())
                } else Toast.makeText(application, "Ошибка получения данных", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: Exception) {
                Toast.makeText(application, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
            }
        }
        else Toast.makeText(application, "Больше ничего нет", Toast.LENGTH_SHORT).show()
    } // - skip
}
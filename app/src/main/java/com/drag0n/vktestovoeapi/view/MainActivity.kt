package com.drag0n.vktestovoeapi.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.drag0n.vktestovoeapi.Const.KEY_INTENT
import com.drag0n.vktestovoeapi.data.Product
import com.drag0n.vktestovoeapi.databinding.ActivityMainBinding
import com.drag0n.vktestovoeapi.view.adapters.ProductsAdapter
import com.drag0n.vktestovoeapi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ProductsAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    var input = "" // символы в searchView
    private lateinit var adapter: ProductsAdapter
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            adapter = ProductsAdapter(this@MainActivity)
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
            model.products.observe(this@MainActivity){
                adapter.submitList(it.products)
                if (it.products.isNotEmpty()) elem()
            }
            bNext.setOnClickListener {
               if (input.isEmpty()) model.addProduct()
            }
            bBack.setOnClickListener {
                if (input.isEmpty())  model.backProduct()
            }
            bRestart.setOnClickListener {
                model.getProduct()
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(value: String?): Boolean {
                    model.getSearchProduct(value.toString())
                    input = value.toString()
                    return false
                }

                override fun onQueryTextChange(value: String?): Boolean {
                    if (value.toString().isEmpty()) {
                        model.getProduct()
                        input = value.toString()
                    }
                    return false
                }
            })
        }
    }

    private fun elem(){
        with(binding){
            imageView2.visibility = View.GONE
            bRestart.visibility = View.GONE
            rcView.visibility = View.VISIBLE
            bBack.visibility = View.VISIBLE
            bNext.visibility = View.VISIBLE
            searchView.visibility= View.VISIBLE
        }
    }

    override fun listener(item: Product) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(KEY_INTENT, item)
        startActivity(intent)
    }

}
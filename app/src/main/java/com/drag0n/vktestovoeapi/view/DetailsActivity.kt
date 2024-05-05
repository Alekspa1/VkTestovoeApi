package com.drag0n.vktestovoeapi.view

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drag0n.vktestovoeapi.Const.KEY_INTENT
import com.drag0n.vktestovoeapi.R
import com.drag0n.vktestovoeapi.data.Product
import com.drag0n.vktestovoeapi.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(KEY_INTENT, Product::class.java)
        } else {
            intent.getSerializableExtra(KEY_INTENT) as Product
        }

        with(binding){
            if (item != null){
                val title = "Название: ${item.title}"
                val desc = "Описание: ${item.description}"
                val price = "Цена: ${item.price}"
                val category = "Категория: ${item.category}"
                tvTitleDet.text = title
                tvDecDet.text = desc
                tvCategoryDet.text = category
                tvPriceDet.text = price
                Picasso.get().load(item.thumbnail).into(imageView3)
            }
            button.setOnClickListener {
                finish()
            }

        }
    }
}
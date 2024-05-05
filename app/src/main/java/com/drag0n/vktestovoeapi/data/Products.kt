package com.drag0n.vktestovoeapi.data

data class Products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)
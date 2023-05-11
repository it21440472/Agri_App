package com.example.productmanagementkotlin.models

data class ProductModel(
    var pId: String? = null,
    var pName: String? = null,
    var pPrice: String? = null,
    var pDescription: String? = null,
    var image: String? = ""
)

package com.harrybro.kotlinbasicecommerce

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Item(var name: String, var price: Double, var description: String, @Id var id: String? = null)

class Cart(val cartItems: MutableList<CartItem> = arrayListOf(), @Id val id: String? = null)

class CartItem(var item: Item, var quantity: Int = 1)
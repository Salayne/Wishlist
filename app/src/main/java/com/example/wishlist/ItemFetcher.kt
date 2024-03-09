package com.example.wishlist

class ItemFetcher {
    companion object{
        private val items: MutableList<WishListItem> = mutableListOf()

        fun getItems(): List<WishListItem>{
            return items
        }

        fun addItem(name: String, price: Double, url:String){
            val newItem = WishListItem(name,price,url)
            items.add(newItem)
        }

    }
}
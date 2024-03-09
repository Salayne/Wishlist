package com.example.wishlist


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var itemsList: List<WishListItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val wishlistRv = findViewById<RecyclerView>(R.id.itemsRv)

        itemsList = ItemFetcher.getItems()
        val adapter = WishListAdapter(itemsList)

        wishlistRv.adapter = adapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        val submit = findViewById<Button>(R.id.submit_button)
        submit.setOnClickListener{
            val itemName: String = findViewById<EditText>(R.id.nameInput).text.toString()
            val itemPrice: Double = findViewById<EditText>(R.id.priceInput).text.toString().toDoubleOrNull()?:0.0
            val itemUrl: String = findViewById<EditText>(R.id.urlInput).text.toString()


            ItemFetcher.addItem(itemName,itemPrice,itemUrl)

            adapter.notifyItemInserted(itemsList.size - 1)

            val starImage = findViewById<ImageView>(R.id.wishStar)
            starImage.visibility = View.INVISIBLE


            findViewById<EditText>(R.id.nameInput).text.clear()
            findViewById<EditText>(R.id.priceInput).text.clear()
            findViewById<EditText>(R.id.urlInput).text.clear()


        }





    }
}
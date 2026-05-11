package com.example.spotmart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class single_item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_single_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        find the views by their ids
        val addToCartButton = findViewById<Button>(R.id.mycart)
        val purchaseNowButton = findViewById<Button>(R.id.btnPurchase)


//        intents
        addToCartButton.setOnClickListener {
            val intent = Intent(applicationContext, cart_item_layout::class.java)
            startActivity(intent)
        }

        purchaseNowButton.setOnClickListener {
            val intent = Intent(applicationContext, payment::class.java)
            startActivity(intent)
        }

    }
}
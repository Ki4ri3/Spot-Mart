package com.example.sokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Find the views by use of their ids
        val txtname = findViewById<TextView>(R.id.txtProductName)
        val txtcost = findViewById<TextView>(R.id.txtProductCost)
        val imgproduct = findViewById<ImageView>(R.id.imgProduct)
        val txtdescription = findViewById<TextView>(R.id.txtProductDescription)


//        retrieve the data passed from the previous activity(mainactivity)
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val product_photo = intent.getStringExtra("product_photo")
        val description = intent.getStringExtra("product_description")

//        Update the textview the data passed from the previous activity
        txtname.text = name
        txtcost.text = "Ksh $cost"
        txtdescription.text = description

//        specify the image url
        val imageUrl = "https://keyarie.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgproduct)

//        Find the edittext and the pay now button by use of thier ids
        val phone = findViewById<EditText>(R.id.phone)
        val btnPay = findViewById<Button>(R.id.pay)

//        set on click listener
        btnPay.setOnClickListener {
//            Specify the APi endpoint for making payment
            val api = "https://keyarie.alwaysdata.net/static/images/mpesa_payment"

//            Create a RequestParams
            val data = RequestParams()

//            Insert data into the requestparams
            data.put("amount", cost)
            data.put("phone", phone.text.toString().trim())

//            Import the helper class
            val helper = ApiHelper(applicationContext)

//            Access the post function inside of the helper class
            helper.post(api, data)

//            Clear the phone number from the Edittext
            phone.text.clear()
        }



    }
}
package com.example.spotmart

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.loopj.android.http.RequestParams

class cashierpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find all views by use od their ids
        val productname = findViewById<EditText>(R.id.product_name)
        val productdescription = findViewById<EditText>(R.id.product_description)
        val productcost = findViewById<EditText>(R.id.product_cost)
        val productphoto = findViewById<EditText>(R.id.product_photo)
        val addproductButton = findViewById<Button>(R.id.addproductBtn)
        val logoutButton = findViewById<Button>(R.id.logoutBtn)

//        On click of the signup button we want to register a person
        addproductButton.setOnClickListener {
            val api = "https://keyarie.alwaysdata.net/api/add_product"

            logoutButton.setOnClickListener {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }

//            Create a requestParams => it is where we are going to hold/sore all the data
            val data = RequestParams()

//            Add/append the username,email,password,phone number on the data
            data.put("productname", productname.text.toString().trim())
            data.put("productdescription", productdescription.text.toString().trim())
            data.put("productcost", productcost.text.toString().trim())
            data.put("productphoto", productphoto.text.toString().trim())

//            import the api helper
            val helper = ApiHelper(applicationContext)

//            Inside of the helper class,access the function
            helper.post(api, data)

        }
    }
}
package com.example.spotmart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class couriersignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_couriersignup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find all views by use od their ids
        val courier_name = findViewById<EditText>(R.id.courier_name)
        val courier_email = findViewById<EditText>(R.id.courier_email)
        val courier_password = findViewById<EditText>(R.id.courier_password)
        val courier_phone = findViewById<EditText>(R.id.courier_phone)
        val signupButton = findViewById<Button>(R.id.signupBtn)
        val signinTextView = findViewById<TextView>(R.id.signintxt)
        val AboutButton = findViewById<Button>(R.id.aboutBtn)


//        Below,when a person clicks on the textview,is taken to the signin page
        signinTextView.setOnClickListener {
            val intent = Intent(applicationContext, couriersignin::class.java)
            startActivity(intent)
        }

        AboutButton.setOnClickListener {
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)
        }

//        On click of the signup button we want to register a person
        signupButton.setOnClickListener {
            val api = "https://keyarie.alwaysdata.net/api/signupcourier"

//            Create a requestParams => it is where we are going to hold/sore all the data
            val data = RequestParams()

//            Add/append the username,email,password,phone number on the data
            data.put("courier_name", courier_name.text.toString().trim())
            data.put("courier_email", courier_email.text.toString().trim())
            data.put("courier_password", courier_password.text.toString().trim())
            data.put("courier_phone", courier_phone.text.toString().trim())

//            import the api helper
            val helper = ApiHelper(applicationContext)

//            Inside of the helper class,access the function
            helper.post(api, data)

        }
    }
}
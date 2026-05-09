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

class couriersignin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_couriersignin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Find the two edit texts, a button and a textview by use of their ids
        val courier_name = findViewById<EditText>(R.id.username)
        val courier_password = findViewById<EditText>(R.id.password)
        val signinButton = findViewById<Button>(R.id.couriersigninBtn)
        val signupTextView = findViewById<TextView>(R.id.signuptxt)
        val AboutButton = findViewById<Button>(R.id.aboutBtn)


//        On the textview set on click listener such that when clicked it navigates you to the signup page
        signupTextView.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }

        AboutButton.setOnClickListener {
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)
        }

//        On click of the button signin, we need to interact with the API endpoints as we pass the two data info email and password
        signinButton.setOnClickListener {
//            Specify the API endpoint
            val api = "https://keyarie.alwaysdata.net/api/signincourier"

//            Create a RequestParams tht will enable you hold the data in form of a bundle/package
            val data = RequestParams()

//            Add/append/attach the email and the password
            data.put("courier_name", courier_name.text.toString())
            data.put("password", courier_password.text.toString())

//            Import the API helper
            val helper = ApiHelper(applicationContext)

//            /By use of the function post_login inside of the helper class, post your data
            helper.post_login(api, data)
        }
    }
}
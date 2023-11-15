package com.example.unipass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StartActivity : AppCompatActivity() {

    private lateinit var login: Button
    private lateinit var register: TextView
    private lateinit var username: EditText
    private lateinit var password: EditText

    public override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var viewModel = ViewModelProvider(this).get(StartActivityViewModel::class.java)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("users")

        myRef.setValue("Hello, World!")

        username = findViewById(R.id.master_username_input)
        password = findViewById(R.id.master_password_input)
        login = findViewById(R.id.login_button)

        register = findViewById(R.id.register_option)

        // Set the onClickListener for both the login button and the register TextView
        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        login.setOnClickListener {
            val txtUsername = username.text.toString()
            val txtPassword = password.text.toString()
            loginUser(txtUsername, txtPassword)
        }
    }

    private fun loginUser(username: String, password: String) {

    }
}

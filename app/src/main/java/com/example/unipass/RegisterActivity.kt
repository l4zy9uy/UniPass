package com.example.unipass

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.unipass.datapack.UserViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var yourName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var register: Button
    private lateinit var userViewModel: UserViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initVariable()

        register.setOnClickListener {
            val txtYourName = yourName.text.toString()
            val txtEmail = email.text.toString()
            val txtPassword = password.text.toString()
            val txtConfirmPassword = confirmPassword.text.toString()
            val salt = userViewModel.generateSalt()

            if(check(txtYourName, txtEmail, txtPassword, txtConfirmPassword)) {
                val user = userViewModel.registerUser(txtYourName, txtEmail, txtPassword, salt)
                userViewModel.addUser(user)

                Toast.makeText(this, "Registering user successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun initVariable() {
        yourName = findViewById(R.id.Your_name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.master_password_input)
        confirmPassword = findViewById(R.id.confirm_password)
        register = findViewById(R.id.register_button)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }
    private fun check(txtYourName: String, txtEmail: String, txtPassword: String, txtConfirmPassword: String) : Boolean {
        if (txtYourName.isBlank() || txtEmail.isBlank() || txtPassword.isBlank() || txtEmail.isBlank()) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        } else if (txtPassword.length < 8) {
            Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show()
        } else if(txtConfirmPassword != txtPassword) {
            Toast.makeText(this, "Confirm password incorrect", Toast.LENGTH_SHORT).show()
        } else {
            return true
        }
        return false
    }
}

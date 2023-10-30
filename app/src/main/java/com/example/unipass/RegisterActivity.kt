package com.example.unipass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.Normalizer

class RegisterActivity : AppCompatActivity() {
    private lateinit var yourName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var register: Button
    private lateinit var map: MutableMap<String, Any>

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        database = Firebase.database.reference
        database.setValue("Hi")

        yourName = findViewById(R.id.Your_name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.master_password_input)
        confirmPassword = findViewById(R.id.confirm_password)
        register = findViewById(R.id.register_button)
        map = mutableMapOf()

        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            val txtYourName = yourName.text.toString()
            val txtEmail = email.text.toString()
            val txtPassword = password.text.toString()
            val txtConfirmPassword = confirmPassword.text.toString()

            if (txtYourName.isBlank() || txtEmail.isBlank() || txtPassword.isBlank() || txtEmail.isBlank()) {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            } else if (txtPassword.length < 8) {
                Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show()
            } else if(txtConfirmPassword != txtPassword) {
                Toast.makeText(this, "Confirm password incorrect", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(txtYourName, txtEmail, txtPassword)
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        val hashedPassword = password //hashPassword(password)
        auth.createUserWithEmailAndPassword(email, hashedPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    writeNewUser(email, name)
                    Toast.makeText(this, "Registering user successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    /*private fun hashPassword(password: String) : String {
        val normalizedPassword = password.trim().unaccent()
        val hashedPassword =
    }*/

    private fun writeNewUser(email: String, name: String) {
        User(name, email)
        map["Name"] = name
        map["Email"] = email
        database.child("Users").updateChildren(map)
    }

    private fun CharSequence.unaccent(): String {
        val regexUnaccented = "\\p{InCombiningDiacriticalMarks}+".toRegex()

        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return regexUnaccented.replace(temp, "")
    }
}

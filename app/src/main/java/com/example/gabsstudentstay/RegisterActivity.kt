package com.example.gabsstudentstay

import android.os.Bundle
import android.widget.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {

            val userEmail = email.text.toString()
            val userPassword = password.text.toString()
            val userConfirmPassword  = confirmPassword.text.toString()

            // Validation section
            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userPassword != userConfirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userPassword.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Firebase Registration Part
            auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->  if (task.isSuccessful) {
                Toast.makeText(this,"Registration Successful", Toast.LENGTH_SHORT).show()
                finish() // Takes user back to login
            } else {
                Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
            }
        }
    }
}
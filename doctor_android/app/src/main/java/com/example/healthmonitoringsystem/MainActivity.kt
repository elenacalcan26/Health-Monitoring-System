package com.example.healthmonitoringsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.editTextTextPersonName)
        password = findViewById(R.id.editTextTextPassword)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            login()
        }
    }

    fun login() {
        var usernameText = username.text
        var passwordText = password.text

        if (usernameText.isBlank() || passwordText.isBlank()) {
            Toast.makeText(
                this, "Username and password can't be blank", Toast.LENGTH_SHORT
            )
                .show()
            return

        }

        // TODO: send http request to confirm the entity
    }
}
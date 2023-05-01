package com.example.healthmonitoringsystem

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthmonitoringsystem.models.DefResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    private lateinit var loginButton: Button

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.editTextTextPersonName)
        password = findViewById(R.id.editTextTextPassword)
        loginButton = findViewById(R.id.loginButton)

        sharedPreferences = (application as HealthMonitoringSystemAndroidApp).sharedPreferences

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

        RetrofitClient.instance.loginUser(usernameText.toString(), passwordText.toString())
            .enqueue(object: Callback<DefResp> {
                override fun onResponse(call: Call<DefResp>, response: Response<DefResp>) {
                    if (response.isSuccessful) {
                        val defResp = response.body()
                        val token = defResp?.token
                        Log.d("Login", token.toString())

                        // save jwt token in SharedPreferences as token: "adjlakdj"
                        var editor = sharedPreferences.edit()
                        editor.putString("token", token.toString())
                        editor.commit()

                        // User logged in => start Main Menu Activity
                        val intent = Intent(this@MainActivity, MainMenuActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Login failed: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<DefResp>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Login failed: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Login", t.message.toString())
                }
            })
    }
}

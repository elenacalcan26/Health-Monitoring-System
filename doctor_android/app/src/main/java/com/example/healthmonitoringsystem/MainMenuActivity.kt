package com.example.healthmonitoringsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.healthmonitoringsystem.models.DocProfileResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMenuActivity : AppCompatActivity() {
    private lateinit var profileButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        profileButton = findViewById(R.id.profile_button)

        profileButton.setOnClickListener {
            getDocProfile()
        }
    }

    fun getDocProfile() {
        RetrofitClient.instance.getDocProfile().enqueue(
            object: Callback<DocProfileResp> {
                override fun onResponse(call: Call<DocProfileResp>, response: Response<DocProfileResp>) {
                    if (response.isSuccessful) {
                        val docProfile = response.body()
                        Log.d("MainMenuViewDocProfile", docProfile.toString())
                    } else {
                        Toast.makeText(
                            this@MainMenuActivity,
                            "Get profile failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<DocProfileResp>, t: Throwable) {
                    Toast.makeText(
                        this@MainMenuActivity,
                        "Get profile failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("MainMenuViewDocProfile", t.message.toString())
                }
            }
        )
    }

}
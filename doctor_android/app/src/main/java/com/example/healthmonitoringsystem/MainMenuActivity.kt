package com.example.healthmonitoringsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.healthmonitoringsystem.viewmodel.DocProfileViewModel
import com.example.healthmonitoringsystem.common.Result


class MainMenuActivity : AppCompatActivity() {
    private lateinit var profileButton: Button
    private lateinit var docProfileViewModel: DocProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        profileButton = findViewById(R.id.profile_button)

        docProfileViewModel = ViewModelProvider(this).get(DocProfileViewModel::class.java)

        profileButton.setOnClickListener {
            viewProfilePressed()
        }
    }

    private fun viewProfilePressed() {
        docProfileViewModel.getDocProfile().observe(
            this
        ) { result ->
            when (result) {
                is Result.Success -> {
                    val intent: Intent = Intent(this, DoctorProfileActivity::class.java).apply {
                        putExtra("docProfile", result.data as Parcelable)
                    }

                    startActivity(intent)
                }

                is Result.Error -> {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
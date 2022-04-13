package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        viewModel.onCreate()

        viewModel.weatherInfo.observe(this) {
            Snackbar.make(binding.root, "success", Snackbar.LENGTH_SHORT).show()
        }

        viewModel.errorHandler.observe(this) {
            Snackbar.make(binding.root, "error", Snackbar.LENGTH_SHORT).show()
        }
    }
}

package com.example.weatherapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        viewModel.onCreate()

        viewModel.weatherInfo.observe(this) {
            Snackbar.make(binding.root, "success", Snackbar.LENGTH_SHORT).show()
        }

        viewModel.errorHandler.observe(this) {
            Snackbar.make(binding.root, "error", Snackbar.LENGTH_SHORT).show()
        }
    }
}

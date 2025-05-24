package com.example.batteryhealthmonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.batteryhealthmonitor.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val temp = binding.etTemp.text.toString().toFloatOrNull()
            val cycles = binding.etCycles.text.toString().toIntOrNull()
            val health = binding.etHealth.text.toString().toIntOrNull()

            if (temp == null || cycles == null || health == null) {
                binding.tvResult.text = "Please enter valid inputs"
                return@setOnClickListener
            }

            val status = when {
                health > 80 && temp < 40 && cycles < 300 -> "Battery is healthy 👍"
                health in 50..80 && temp < 50 && cycles < 500 -> "Battery condition is average. Monitor closely."
                else -> "Battery health is poor. Consider swapping soon! ⚠️"
            }
            binding.tvResult.text = status
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
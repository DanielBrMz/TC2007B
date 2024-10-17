package com.example.ui_development

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FeatureSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_selection)

        val btnTemperatureConverter = findViewById<Button>(R.id.btnTemperatureConverter)
        btnTemperatureConverter.setOnClickListener {
            val intent = Intent(this, TemperatureConverterActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.ui_development

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TemperatureConverterActivity : AppCompatActivity() {
    private lateinit var etCelsius: EditText
    private lateinit var btnConvert: Button
    private lateinit var tvFahrenheit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature_converter)

        etCelsius = findViewById(R.id.etCelsius)
        btnConvert = findViewById(R.id.btnConvert)
        tvFahrenheit = findViewById(R.id.tvFahrenheit)

        btnConvert.setOnClickListener {
            val celsius = etCelsius.text.toString().toDoubleOrNull()
            if (celsius != null) {
                val fahrenheit = celsius * 9/5 + 32
                tvFahrenheit.text = String.format("%.1f°F", fahrenheit)
            } else {
                tvFahrenheit.text = "Invalid input"
            }
        }
    }
}
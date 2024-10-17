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
        val btnRockPaperScissors = findViewById<Button>(R.id.btnRockPaperScissors)
        val btnQuadraticEquation = findViewById<Button>(R.id.btnQuadraticEquation)

        btnTemperatureConverter.setOnClickListener {
            startActivity(Intent(this, TemperatureConverterActivity::class.java))
        }

        btnRockPaperScissors.setOnClickListener {
            startActivity(Intent(this, RockPaperScissorsActivity::class.java))
        }

        btnQuadraticEquation.setOnClickListener {
            startActivity(Intent(this, QuadraticEquationSolverActivity::class.java))
        }
    }
}
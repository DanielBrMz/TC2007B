package com.example.ui_development

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class QuadraticEquationSolverActivity : AppCompatActivity() {
    private lateinit var etA: EditText
    private lateinit var etB: EditText
    private lateinit var etC: EditText
    private lateinit var btnSolve: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quadratic_equation_solver)

        etA = findViewById(R.id.etA)
        etB = findViewById(R.id.etB)
        etC = findViewById(R.id.etC)
        btnSolve = findViewById(R.id.btnSolve)
        tvResult = findViewById(R.id.tvResult)

        btnSolve.setOnClickListener { solveEquation() }
    }

    private fun solveEquation() {
        val a = etA.text.toString().toDoubleOrNull()
        val b = etB.text.toString().toDoubleOrNull()
        val c = etC.text.toString().toDoubleOrNull()

        if (a == null || b == null || c == null) {
            tvResult.text = "Por favor, ingrese valores válidos para a, b y c."
            return
        }

        if (a == 0.0) {
            tvResult.text = "No es una ecuación cuadrática (a = 0)."
            return
        }

        val discriminant = b * b - 4 * a * c
        val result = when {
            discriminant > 0 -> {
                val x1 = (-b + sqrt(discriminant)) / (2 * a)
                val x2 = (-b - sqrt(discriminant)) / (2 * a)
                "x₁ = ${"%.2f".format(x1)}, x₂ = ${"%.2f".format(x2)}"
            }
            discriminant == 0.0 -> {
                val x = -b / (2 * a)
                "x = ${"%.2f".format(x)} (raíz doble)"
            }
            else -> {
                val realPart = -b / (2 * a)
                val imaginaryPart = sqrt(-discriminant) / (2 * a)
                "x₁ = ${"%.2f".format(realPart)} + ${"%.2f".format(imaginaryPart)}i, " +
                        "x₂ = ${"%.2f".format(realPart)} - ${"%.2f".format(imaginaryPart)}i"
            }
        }

        tvResult.text = result
    }
}
package com.example.ui_development
import kotlin.math.sqrt

class EcuacionSegundoGradoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecuacion_segundo_grado)

        val etA = findViewById<EditText>(R.id.etA)
        val etB = findViewById<EditText>(R.id.etB)
        val etC = findViewById<EditText>(R.id.etC)
        val btnResolver = findViewById<Button>(R.id.btnResolver)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnResolver.setOnClickListener {
            val a = etA.text.toString().toDoubleOrNull()
            val b = etB.text.toString().toDoubleOrNull()
            val c = etC.text.toString().toDoubleOrNull()

            if (a == null || b == null || c == null) {
                tvResultado.text = "Por favor, ingrese números válidos"
                return@setOnClickListener
            }

            if (a == 0.0) {
                tvResultado.text = "No es una ecuación de segundo grado (a = 0)"
                return@setOnClickListener
            }

            val discriminante = b * b - 4 * a * c
            val resultado = when {
                discriminante > 0 -> {
                    val x1 = (-b + sqrt(discriminante)) / (2 * a)
                    val x2 = (-b - sqrt(discriminante)) / (2 * a)
                    "x₁ = %.2f, x₂ = %.2f".format(x1, x2)
                }
                discriminante == 0.0 -> {
                    val x = -b / (2 * a)
                    "x = %.2f".format(x)
                }
                else -> {
                    val realPart = -b / (2 * a)
                    val imaginaryPart = sqrt(-discriminante) / (2 * a)
                    "x₁ = %.2f + %.2fi, x₂ = %.2f - %.2fi".format(realPart, imaginaryPart, realPart, imaginaryPart)
                }
            }

            tvResultado.text = resultado
        }
    }
}
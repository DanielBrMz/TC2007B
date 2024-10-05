package com.example.ui_development

class ConversorTemperaturaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor_temperatura)

        val etCelsius = findViewById<EditText>(R.id.etCelsius)
        val btnConvertir = findViewById<Button>(R.id.btnConvertir)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnConvertir.setOnClickListener {
            val celsius = etCelsius.text.toString().toDoubleOrNull()
            if (celsius != null) {
                val fahrenheit = celsius * 9/5 + 32
                tvResultado.text = "%.2f°C = %.2f°F".format(celsius, fahrenheit)
            } else {
                tvResultado.text = "Por favor, ingrese un número válido"
            }
        }
    }
}
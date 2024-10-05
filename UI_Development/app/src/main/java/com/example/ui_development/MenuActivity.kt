package com.example.ui_development

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.btnConversor).setOnClickListener {
            startActivity(Intent(this, ConversorTemperaturaActivity::class.java))
        }

        findViewById<Button>(R.id.btnPPT).setOnClickListener {
            startActivity(Intent(this, PiedraPapelTijeraActivity::class.java))
        }

        findViewById<Button>(R.id.btnEcuacion).setOnClickListener {
            startActivity(Intent(this, EcuacionSegundoGradoActivity::class.java))
        }
    }
}
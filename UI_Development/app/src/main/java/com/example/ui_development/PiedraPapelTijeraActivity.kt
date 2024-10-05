package com.example.ui_development

class PiedraPapelTijeraActivity : AppCompatActivity() {
    private var playerScore = 0
    private var computerScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piedra_papel_tijera)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        val options = listOf("Piedra", "Papel", "Tijera")

        fun play(playerChoice: String) {
            val computerChoice = options.random()
            val result = when {
                playerChoice == computerChoice -> "Empate"
                (playerChoice == "Piedra" && computerChoice == "Tijera") ||
                        (playerChoice == "Papel" && computerChoice == "Piedra") ||
                        (playerChoice == "Tijera" && computerChoice == "Papel") -> {
                    playerScore++
                    "Ganaste"
                }
                else -> {
                    computerScore++
                    "Perdiste"
                }
            }
            tvScore.text = "Jugador: $playerScore - Computadora: $computerScore"
            tvResultado.text = "Elegiste $playerChoice, la computadora eligió $computerChoice. $result."
        }

        findViewById<Button>(R.id.btnPiedra).setOnClickListener { play("Piedra") }
        findViewById<Button>(R.id.btnPapel).setOnClickListener { play("Papel") }
        findViewById<Button>(R.id.btnTijera).setOnClickListener { play("Tijera") }
    }
}
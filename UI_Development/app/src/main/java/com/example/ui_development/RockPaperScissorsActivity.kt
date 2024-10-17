package com.example.ui_development

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RockPaperScissorsActivity : AppCompatActivity() {
    private lateinit var ivPlayerChoice: ImageView
    private lateinit var ivComputerChoice: ImageView
    private lateinit var tvResult: TextView
    private lateinit var btnRock: Button
    private lateinit var btnPaper: Button
    private lateinit var btnScissors: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissors)

        ivPlayerChoice = findViewById(R.id.ivPlayerChoice)
        ivComputerChoice = findViewById(R.id.ivComputerChoice)
        tvResult = findViewById(R.id.tvResult)
        btnRock = findViewById(R.id.btnRock)
        btnPaper = findViewById(R.id.btnPaper)
        btnScissors = findViewById(R.id.btnScissors)

        btnRock.setOnClickListener { playGame("rock") }
        btnPaper.setOnClickListener { playGame("paper") }
        btnScissors.setOnClickListener { playGame("scissors") }
    }

    private fun playGame(playerChoice: String) {
        val choices = listOf("rock", "paper", "scissors")
        val computerChoice = choices.random()

        ivPlayerChoice.setImageResource(getChoiceImage(playerChoice))
        ivComputerChoice.setImageResource(getChoiceImage(computerChoice))

        val result = when {
            playerChoice == computerChoice -> "Empate!"
            (playerChoice == "rock" && computerChoice == "scissors") ||
                    (playerChoice == "paper" && computerChoice == "rock") ||
                    (playerChoice == "scissors" && computerChoice == "paper") -> "Ganaste!"
            else -> "La computadora gana!"
        }

        tvResult.text = result
    }

    private fun getChoiceImage(choice: String): Int {
        return when (choice) {
            "rock" -> R.drawable.rock
            "paper" -> R.drawable.paper
            "scissors" -> R.drawable.scissors
            else -> throw IllegalArgumentException("Elección inválida")
        }
    }
}
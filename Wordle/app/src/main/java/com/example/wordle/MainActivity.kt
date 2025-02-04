package com.example.wordle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var guessCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val guess_one_input_label = findViewById<TextView>(R.id.guess_one_input_label)
        val guess_one_input_check_label = findViewById<TextView>(R.id.guess_one_input_check_label)
        val guess_two_input_label = findViewById<TextView>(R.id.guess_two_input_label)
        val guess_two_input_check_label = findViewById<TextView>(R.id.guess_two_input_check_label)
        val guess_three_input_label = findViewById<TextView>(R.id.guess_three_input_label)
        val guess_three_input_check_label = findViewById<TextView>(R.id.guess_three_input_check_label)

        val word_to_guess_label = findViewById<TextView>(R.id.word_to_guess)
        val simpleEditText = findViewById<EditText>(R.id.input_textbox);
        val guess_btn = findViewById<Button>(R.id.guess_btn)

        word_to_guess_label.text = wordToGuess
        word_to_guess_label.visibility =  View.INVISIBLE

        var strValue = ""

        guess_btn.setOnClickListener {
            strValue = simpleEditText.text.toString().uppercase()
            if(guessCount == 0){
                guess_one_input_label.text = strValue
                guess_one_input_check_label.text = checkGuess(strValue)
            } else if (guessCount == 1){
                guess_two_input_label.text = strValue
                guess_two_input_check_label.text = checkGuess(strValue)
            } else if (guessCount == 2){
                guess_three_input_label.text = strValue
                guess_three_input_check_label.text = checkGuess(strValue)
                guess_btn.isEnabled = false
                word_to_guess_label.visibility = View.VISIBLE
            }
            guessCount++
        }
    }

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}
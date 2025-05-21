package com.application.quizapp

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // UI elements
    private lateinit var questionText: TextView
    private lateinit var optionButtons: List<Button>
    private lateinit var restartButton: Button
    private lateinit var questionContainer: LinearLayout

    // Flip card animation sets
    private lateinit var frontAnimator: Animator
    private lateinit var backAnimator: Animator
    private var isFlipped = false

    // Quiz content
    private val questions = arrayOf(
        "What is the name of the musical symbol that means loud?",
        "How many strings does a standard guitar have?",
        "What instrument has black and white keys?",
        "What do you call someone who writes music?",
        "What family of instruments does the trumpet belong to?"
    )

    private val options = arrayOf(
        arrayOf("Jazz", "Rock", "Forte"),
        arrayOf("6", "4", "5"),
        arrayOf("Drums", "Piano", "Flute"),
        arrayOf("Composer", "Artist", "Producer"),
        arrayOf("Pipe", "Sax", "Brass")
    )

    private val correctAnswers = arrayOf(2, 0, 1, 0, 2)

    // Quiz state
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link UI components to XML
        questionText = findViewById(R.id.questionText)
        questionContainer = findViewById(R.id.questionContainer)
        restartButton = findViewById(R.id.restartButton)

        // Initialize buttons list
        optionButtons = listOf(
            findViewById(R.id.option1Button),
            findViewById(R.id.option2Button),
            findViewById(R.id.option3Button),
            findViewById(R.id.option4Button),
            findViewById(R.id.option5Button)
        )

        // Set up animations
        setupFlipAnimations()

        // Load first question
        loadQuestion()

        // Set up listeners for option buttons
        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener { checkAnswer(index) }
        }

        // Restart quiz when button is pressed
        restartButton.setOnClickListener {
            if (isFlipped) flipCardBack()
            currentQuestionIndex = 0
            score = 0
            loadQuestion()
            restartButton.isEnabled = false
        }
    }

    // Initializes the flip animations
    private fun setupFlipAnimations() {
        val scale = applicationContext.resources.displayMetrics.density
        questionContainer.cameraDistance = 8000 * scale

        frontAnimator = AnimatorInflater.loadAnimator(this, R.animator.out_animation)
        backAnimator = AnimatorInflater.loadAnimator(this, R.animator.in_animation)
    }

    // Flips to the back
    private fun flipCard() {
        frontAnimator.setTarget(questionContainer)
        backAnimator.setTarget(questionContainer)
        frontAnimator.start()
        backAnimator.start()
        isFlipped = true
    }

    // Flips back to front
    private fun flipCardBack() {
        frontAnimator.setTarget(questionContainer)
        backAnimator.setTarget(questionContainer)

        if (frontAnimator.isRunning || backAnimator.isRunning) {
            frontAnimator.end()
            backAnimator.end()
        }

        frontAnimator.start()
        backAnimator.start()
        isFlipped = false
    }

    // Loads the current question and sets options
    @SuppressLint("SetTextI18n")
    private fun loadQuestion() {
        if (currentQuestionIndex >= questions.size) {
            questionText.text = "Quiz Finished! Score: $score/${questions.size}"
            optionButtons.forEach { it.isEnabled = false }
            restartButton.isEnabled = true
            return
        }

        // Set question text
        questionText.text = questions[currentQuestionIndex]

        // Display answer options
        optionButtons.forEachIndexed { i, button ->
            if (i < options[currentQuestionIndex].size) {
                button.text = options[currentQuestionIndex][i]
                button.visibility = View.VISIBLE
                button.isEnabled = true
                button.setBackgroundColor(Color.LTGRAY)
            } else {
                button.visibility = View.GONE
            }
        }
    }

    // Handles answer checking
    private fun checkAnswer(selectedIndex: Int) {
        val correctIndex = correctAnswers[currentQuestionIndex]

        if (selectedIndex == correctIndex) {
            optionButtons[selectedIndex].setBackgroundColor(Color.GREEN)
            score++
        } else {
            optionButtons[selectedIndex].setBackgroundColor(Color.RED)
            optionButtons[correctIndex].setBackgroundColor(Color.GREEN)
        }

        // Disable all options
        optionButtons.forEach { it.isEnabled = false }

        // Animate card flip
        flipCard()

        // Delay loading next question
        optionButtons[correctIndex].postDelayed({
            flipCardBack()
            currentQuestionIndex++
            loadQuestion()
        },2000)
        }
}
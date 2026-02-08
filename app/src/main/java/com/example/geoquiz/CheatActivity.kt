package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class CheatActivity : ComponentActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    private var answerIsTrue: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        showAnswerButton.setOnClickListener {
            val answerTextResId = if (answerIsTrue) {
                R.string.true_button
            } else {
                R.string.false_button
            }
            answerTextView.setText(answerTextResId)

            val data = Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, true)
            }
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}

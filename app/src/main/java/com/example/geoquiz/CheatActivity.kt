package com.example.geoquiz

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

private const val KEY_DID_SHOW_ANSWER = "did_show_answer"

class CheatActivity : ComponentActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    private var answerIsTrue: Boolean = false
    private var didShowAnswer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        didShowAnswer = savedInstanceState?.getBoolean(KEY_DID_SHOW_ANSWER, false) ?: false

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        if (didShowAnswer) {
            val answerTextResId = if (answerIsTrue) {
                R.string.true_button
            } else {
                R.string.false_button
            }
            answerTextView.setText(answerTextResId)
            setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_ANSWER_SHOWN, true))
            showAnswerButton.visibility = View.INVISIBLE
        }

        showAnswerButton.setOnClickListener {
            val answerTextResId = if (answerIsTrue) {
                R.string.true_button
            } else {
                R.string.false_button
            }
            answerTextView.setText(answerTextResId)

            didShowAnswer = true
            setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_ANSWER_SHOWN, true))

            // Chapter 6: circular reveal on API 21+; direct hide on older APIs
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val cx = showAnswerButton.width / 2
                val cy = showAnswerButton.height / 2
                val radius = showAnswerButton.width.toFloat()
                val anim = ViewAnimationUtils.createCircularReveal(
                    showAnswerButton, cx, cy, radius, 0f
                )
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        showAnswerButton.visibility = View.INVISIBLE
                    }
                })
                anim.start()
            } else {
                showAnswerButton.visibility = View.INVISIBLE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_DID_SHOW_ANSWER, didShowAnswer)
    }
}

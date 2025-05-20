/* //quiz questions
*By twinkl:
*https://www.twinkl.co.za/homework-help/world-geography-homework-help/africa-facts/south-africa-facts-for-kids
* [Accessed on 18 May 2025]
*
*
* */
package vcmsa.ci.a222

import android.graphics.Color
import android.os.Bundle
import android.text.Selection
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.a222.R.drawable.photo1
import vcmsa.ci.a222.R.drawable.photo2
import vcmsa.ci.a222.R.drawable.photo3
import vcmsa.ci.a222.R.drawable.photo4
import vcmsa.ci.a222.R.drawable.photo5
import kotlin.system.exitProcess


class MainActivity2 : AppCompatActivity() {



    private var imageView: ImageView? = null
    private var tView: TextView? = null

    private var questionIndex = 0
    private var score = 0

    data class PhotoQuestion(
        val imageResId: Int,
        val answer: Boolean
    )

    private val myPhotoQuestions = arrayListOf(
        PhotoQuestion(R.drawable.photo1, false),
        PhotoQuestion(R.drawable.photo2, true),
        PhotoQuestion(R.drawable.photo3, false),
        PhotoQuestion(R.drawable.photo4, false),
        PhotoQuestion(R.drawable.photo5, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        //Finding widgets by they definitive ID's
        tView = findViewById(R.id.tView)
        imageView = findViewById(R.id.imageQuestions)
        val trueBtn = findViewById<Button>(R.id.trueBtn)
        val falseBtn = findViewById<Button>(R.id.falseBtn)
        val nextBtn = findViewById<Button>(R.id.nextBtn)
        val rePlayBtn = findViewById<Button>(R.id.rePlayBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)

//     hidding these to buttons as ill need them when the games ends
        rePlayBtn.visibility = Button.GONE
        exitBtn.visibility = Button.GONE


        // setting set on click listners for button
        trueBtn.setOnClickListener {
            checkBtn(true)
        }

        falseBtn.setOnClickListener {
            checkBtn(false)
        }

        nextBtn.setOnClickListener {
            if (questionIndex < myPhotoQuestions.size - 1) {
                questionIndex++
                next()
                tView?.text = ""
            } else {
                tView?.text = "The End! Your Score is: $score/${myPhotoQuestions.size}"
                Toast.makeText(this, "Well done, game complete!", Toast.LENGTH_LONG).show()

                // Hiding these 3 button when game ends
                trueBtn.isEnabled = false
                falseBtn.isEnabled = false
                nextBtn.isEnabled = false

                // Displaying these 2 button when game ends for user to play again on leave game
                rePlayBtn.visibility = Button.VISIBLE
                exitBtn.visibility = Button.VISIBLE
            }

            rePlayBtn.setOnClickListener {
                questionIndex = 0
                score = 0
                trueBtn.isEnabled = true
                falseBtn.isEnabled = true
                nextBtn.isEnabled = true
                rePlayBtn.visibility = Button.GONE
                exitBtn.visibility = Button.GONE
                tView?.text = ""
                next()
            }
               // Exiting app
            exitBtn.setOnClickListener {
                finishAffinity()
                exitProcess(1)
            }
        }

        // For loading questions //
        next()
    }

    private fun next() {
        val question = myPhotoQuestions[questionIndex]
        imageView?.setImageResource(question.imageResId)
        tView?.text = "Question ${questionIndex + 1}"
    }
         // functions for checking if user input is correct or incorrect//
    private fun checkBtn(userInput: Boolean) {
        val correctAnswer = myPhotoQuestions[questionIndex].answer
        if (userInput == correctAnswer) {
            score++
            tView?.text = "CORRECT!"
        } else {
            tView?.text = "INCORRECT!"
        }
    }
}



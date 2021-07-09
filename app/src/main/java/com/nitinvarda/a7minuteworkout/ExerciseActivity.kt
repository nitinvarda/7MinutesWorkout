package com.nitinvarda.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {

    private var restTimer :CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercies_activity)

        val actionBar = supportActionBar

        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_exercies_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()

    }

    override fun onDestroy() {
        if(restTimer !=null){
            restTimer!!.cancel()
            restProgress =0
        }
        super.onDestroy()

    }

    private fun setRestProgressBar(){
        restTitle.text = "take rest"
        progressBar.progress = restProgress

        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10-restProgress
                tvTimer.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Here now we will start the exercise",Toast.LENGTH_SHORT).show()
                llRestView.visibility = View.GONE
                llExercieView.visibility = View.VISIBLE
                setupExerciseView()
            }
        }.start()
    }

    private fun setExerciseProgressBar(){
        exerciseProgressBar.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                exerciseProgressBar.progress = 30-exerciseProgress
                tvExerciseTimer.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Completed first Exercise",Toast.LENGTH_SHORT).show()
                llRestView.visibility = View.VISIBLE
                llExercieView.visibility = View.GONE
                setupRestView()
            }
        }.start()
    }

    private fun setupExerciseView(){
        if(exerciseTimer !=null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()

    }

    private  fun setupRestView(){
        if(restTimer !=null){
            restTimer!!.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }
}
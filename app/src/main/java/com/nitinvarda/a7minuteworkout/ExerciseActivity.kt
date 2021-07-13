package com.nitinvarda.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    private var restTimer :CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress=0

    private var exercieList:ArrayList<ExerciceModel>? = null

    private  var currentExercicePosition =-1;

    private var tts:TextToSpeech? = null

    private var player:MediaPlayer? = null

    private var exerciseAdapter:ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercies_activity)



        val actionBar = supportActionBar

        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_exercies_activity.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        tts = TextToSpeech(this,this)

        exercieList = Constants.defaultExerciseList()


        setupRestView()
        setupExerciseStatusRecyclerView()

    }

    override fun onDestroy() {
        if(restTimer !=null){
            restTimer!!.cancel()
            restProgress =0
        }
        if(exerciseTimer !=null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null){
            player!!.stop()
        }
        super.onDestroy()

    }

    private fun setRestProgressBar(){

        progressBar.progress = restProgress

        upcomingExercice.text = exercieList!![currentExercicePosition+1].getName()

        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10-restProgress
                tvTimer.text = (10-restProgress).toString()
            }

            override fun onFinish() {
//                Toast.makeText(this@ExerciseActivity,"Here now we will start the exercise",Toast.LENGTH_SHORT).show()

                currentExercicePosition++

                exercieList!![currentExercicePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
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



                //exercieList!!.size - 1
                if(currentExercicePosition < 2){
                    exercieList!![currentExercicePosition].setIsSelected(false)
                    exercieList!![currentExercicePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()

                    restTitle.text = "take rest"
                    setupRestView()
                }else{
//                    Toast.makeText(this@ExerciseActivity,"You have successfully completed Exercise",Toast.LENGTH_SHORT).show()
                    finish()
                    val intent = Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setupExerciseView(){
        llRestView.visibility = View.GONE
        llExercieView.visibility = View.VISIBLE
        if(exerciseTimer !=null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        speakOut(exercieList!![currentExercicePosition].getName())

        setExerciseProgressBar()

        ivImage.setImageResource(exercieList!![currentExercicePosition].getImage())
        tvExerciseName.text = exercieList!![currentExercicePosition].getName()

    }

    private  fun setupRestView(){
        try {
            player = MediaPlayer.create(applicationContext,R.raw.press_start)
            player!!.isLooping = false
            player!!.start()
        }
        catch (e:Exception){
            e.printStackTrace()
        }

        llRestView.visibility = View.VISIBLE
        llExercieView.visibility = View.GONE
        if(restTimer !=null){
            restTimer!!.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){
                val result = tts!!.setLanguage(Locale.US)
                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("TTS","The Language is not supported",)
                }
        }
        else{
            Log.e("TTS","Initialization failed")
        }

    }

    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }


    private fun setupExerciseStatusRecyclerView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        exerciseAdapter = ExerciseStatusAdapter(exercieList!!,this)
        rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun  customDialogForBackButton(){
        var customDialog = Dialog(this)

        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.WHITE))
        customDialog.setContentView(R.layout.dialog_custom_back_confirmation)


        customDialog.tvYes.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener {
            customDialog.dismiss()
        }

        customDialog.show()


    }
}
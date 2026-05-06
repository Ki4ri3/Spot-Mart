package com.example.sokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.style.TtsSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {

//    Declare a variable that will hold the text-to-speech object
    lateinit var tts : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the textview and the button by use of their ids
        val textview = findViewById<TextView>(R.id.aboutTxt)
        val speakButton = findViewById<Button>(R.id.btnlisten)

        tts = TextToSpeech(this){
            if(it == TextToSpeech.SUCCESS){
                tts.language = Locale.ENGLISH
            }
        }
        speakButton.setOnClickListener {
            val text = textview.text.toString()
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null , null )

            val voices = tts.voices

            for (voice in voices) {
                if (voice.name.contains("male", true)) {
                    tts.voice = voice
                    break
                }
            }
         }

    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
package com.oceanbrasil.rmdeadoralivegame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var currentCharacter : Character
    private lateinit var textName: TextView
    private lateinit var imageViewCharacter: ImageView
    private lateinit var textViewScore: TextView
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        textName = findViewById<TextView>(R.id.textViewName)
        imageViewCharacter = findViewById<ImageView>(R.id.imageViewCharacter)
        textViewScore = findViewById<TextView>(R.id.textViewScore)

        textViewScore.text = "Score: $score"

        val buttonAlive = findViewById<Button>(R.id.buttonAlive)
        val buttonDead = findViewById<Button>(R.id.buttonDead)
        buttonAlive.setOnClickListener {
            checkStatus("Alive")
        }
        buttonDead.setOnClickListener {
            checkStatus("Dead")
        }
        loadCharacter()

    }
    fun loadCharacter() {
        val randomId = Random.nextInt(1,826)
        RetrofitInstance.api.getCharacter(randomId).enqueue(object: Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                val character = response.body()
                character?.let {
                    Log.d("Resposta", it.name)
                    currentCharacter = it
                    textName.text = it.name
                    Glide.with(this@GameActivity)
                        .load(it.image)
                        .into(imageViewCharacter)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("Erro", t.message.toString())
            }
        })
    }

    fun checkStatus(status: String) {
        if (currentCharacter.status == status) {
            Toast.makeText(this@GameActivity, "Você acertou!", Toast.LENGTH_SHORT).show()
            score += 1
            textViewScore.text = "Score: $score"
        } else {
            Toast.makeText(this@GameActivity, "Você errou!", Toast.LENGTH_SHORT).show()
        }
        loadCharacter()
    }
}
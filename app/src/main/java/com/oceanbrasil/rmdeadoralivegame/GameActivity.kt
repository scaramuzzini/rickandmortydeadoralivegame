package com.oceanbrasil.rmdeadoralivegame

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        val textName = findViewById<TextView>(R.id.textViewName)

        RetrofitInstance.api.getCharacter(1).enqueue(object: Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                val character = response.body()
                character?.let {
                    Log.d("Resposta", it.name)
                    textName.text = it.name
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("Erro", t.message.toString())
            }
        })


    }
}
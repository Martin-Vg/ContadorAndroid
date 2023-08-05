package com.martinvalenzuela.contador

import android.app.PictureInPictureUiState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.content.Context


class MainActivity : AppCompatActivity() {
    lateinit var rs_cuenta : TextView
    var cuenta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rs_cuenta = findViewById(R.id.textView)
        val btn_mas: ImageButton = findViewById(R.id.imageButton)
        val btn_menos: ImageButton = findViewById(R.id.imageButton2)
        val btn_reset: ImageButton = findViewById(R.id.imageButton4)
        btn_mas.setOnClickListener(){
            cuenta++
            rs_cuenta.setText("$cuenta")
        }
        btn_menos.setOnClickListener(){
            cuenta--
            rs_cuenta.setText("$cuenta")
        }
        btn_reset.setOnClickListener(){
            cuenta = 0
            rs_cuenta.setText("$cuenta")
        }
    }

    override fun onPause() {
        super.onPause()

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putInt("contador", cuenta)
        editor?.apply()
    }

    override fun onStart() {
        super.onStart()

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?:return
        val contador = sharedPref.getInt("contador", 0)
        cuenta = contador
        rs_cuenta.setText("$contador")

    }
}
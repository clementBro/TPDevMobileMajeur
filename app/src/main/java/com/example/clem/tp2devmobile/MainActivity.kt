package com.example.clem.tp2devmobile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get UI coponent
        val redirect1 = findViewById<Button>(R.id.redirect1)
        val redirect2 = findViewById<Button>(R.id.redirect2)

        //redirect on click
        redirect1.setOnClickListener(){
            val intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }

        redirect2.setOnClickListener(){
            val intent = Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }
    }
}

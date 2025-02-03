package com.example.simplecounter

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<ImageButton>(R.id.button)
        val upgradeBtn = findViewById<Button>(R.id.button2)

        val textView = findViewById<TextView>(R.id.textView)
        btn.setOnClickListener {
            bounceAnimation(btn)

            counter += 1
            textView.text = counter.toString()
            if (counter >=100) {

                // Show upgrade button and set onClickListener
                upgradeBtn.visibility = View.VISIBLE
                upgradeBtn.setOnClickListener {
                    btn.setOnClickListener {
                        bounceAnimation(btn)
                        counter += 2
                        textView.text = counter.toString()
                    }

                    // Hide upgrade button again
                    upgradeBtn.visibility = View.INVISIBLE
                }
            }
        }

    }

   fun bounceAnimation(view: View){
       val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1.1f, 1.0f)
       val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1.1f, 1.0f)

       ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
           duration = 300
           interpolator = OvershootInterpolator()
           start()
       }

   }
}
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
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private var currentGoal = 25
    private var totalGoalsCount = 0
    private var clicker = 1
    private var nextUpgrade = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<ImageButton>(R.id.clickerBtn)
        val upgradeBtn = findViewById<Button>(R.id.upgradeBtn)
        val totalGoalsCountLabel = findViewById<TextView>(R.id.totalGoalsCountLabel)
        val currentGoalLabel = findViewById<TextView>(R.id.currentGoalCountLabel)
        val textView = findViewById<TextView>(R.id.counter)
        val nextUpgradeLabel = findViewById<TextView>(R.id.nextUpgradeLabel)
        val nextUpgradeTextLabel = findViewById<TextView>(R.id.nextUpgradeTextLabel)

        currentGoalLabel.text = "$currentGoal"

        btn.setOnClickListener {
            bounceAnimation(btn)

            counter += clicker
            textView.text = "$counter"

            if (counter >= currentGoal) {
                totalGoalsCount++
                currentGoal = (currentGoal * 1.25).toInt()
                currentGoalLabel.text = "$currentGoal"
                totalGoalsCountLabel.text = "$totalGoalsCount"
                bounceAnimation(currentGoalLabel)
                bounceAnimation(totalGoalsCountLabel)
            }

            if (counter >= nextUpgrade) {
                var tempClicker = clicker * 2
                upgradeBtn.text = "Upgrade clicker to $tempClicker"
                upgradeBtn.visibility = View.VISIBLE
                nextUpgradeTextLabel.visibility = View.INVISIBLE
                nextUpgradeLabel.visibility = View.INVISIBLE
            }
        }

        upgradeBtn.setOnClickListener {
            bounceAnimation(btn)
            clicker *= 2
            nextUpgrade = counter + 100 * clicker
            Toast.makeText(this, "Upgraded! Clicker now $clicker! Next upgrade at $nextUpgrade", Toast.LENGTH_SHORT).show()
            upgradeBtn.visibility = View.INVISIBLE

            nextUpgradeLabel.text = "$nextUpgrade"
            nextUpgradeTextLabel.visibility = View.VISIBLE
            nextUpgradeLabel.visibility = View.VISIBLE
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
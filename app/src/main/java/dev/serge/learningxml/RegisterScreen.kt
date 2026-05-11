package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reigster_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val spinner = findViewById<Spinner>(R.id.gender)

        val gender = arrayOf(
            "Male",
            "Female",
            "Others"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            gender
        )

        spinner.adapter = adapter

        val login = findViewById<TextView>(R.id.login)
        val skipToHome = findViewById<TextView>(R.id.skipToHome)

        login.setOnClickListener {

            val intent = Intent(
                this,
                LoginScreen::class.java
            )

            startActivity(intent)
        }

        skipToHome.setOnClickListener {

            val intent = Intent(
                this,
                HomeScreen::class.java
            )

            startActivity(intent)
        }

    }
}
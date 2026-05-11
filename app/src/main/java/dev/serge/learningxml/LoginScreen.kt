package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_screen)
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

        val signUp = findViewById<TextView>(R.id.signUp)
        val skipToHome = findViewById<TextView>(R.id.skipToHome)

        signUp.setOnClickListener {

            val intent = Intent(
                this,
                RegisterScreen::class.java
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
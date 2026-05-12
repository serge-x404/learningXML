package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.serge.learningxml.databinding.ActivityLiveUpdatesBinding
import kotlin.math.PI

class LiveUpdates : AppCompatActivity() {

    private lateinit var binding: ActivityLiveUpdatesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_live_updates)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityLiveUpdatesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.selectedItemId = R.id.nav_updates
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_events -> {
                    startActivity(Intent(this, HomeScreen::class.java))
                    finish()
                }

                R.id.nav_places -> {
                    startActivity(Intent(this, LivePlacesScreen::class.java))
                    finish()
                }
                R.id.nav_shorts -> {}
                R.id.nav_updates -> {}
            }
            true
        }
        val closeDrawer = findViewById<ImageView>(R.id.closeDrawer)

        closeDrawer.setOnClickListener {
            binding.main.closeDrawer(GravityCompat.START)
        }

        binding.menuButton.setOnClickListener {
            binding.main.openDrawer(GravityCompat.START)
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(this,Profile::class.java))
        }
    }
}
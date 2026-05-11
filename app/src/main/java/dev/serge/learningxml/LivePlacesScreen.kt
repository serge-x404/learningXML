package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.serge.learningxml.databinding.ActivityLivePlacesScreenBinding

class LivePlacesScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLivePlacesScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_live_places_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityLivePlacesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categories = listOf(
            Category("Hotel",android.R.drawable.ic_menu_compass),
            Category("Shows",android.R.drawable.ic_media_play),
            Category("Sports",android.R.drawable.ic_menu_gallery),
            Category("Yoga",android.R.drawable.ic_menu_manage),
            Category("Education",android.R.drawable.ic_menu_info_details),
            Category("Activities",android.R.drawable.ic_menu_camera),
        )
        val adapter = LivePlacesCategoryAdapter(categories)

        binding.categoryRecycler.layoutManager = GridLayoutManager(this, 3)
        binding.categoryRecycler.adapter = adapter

        binding.bottomNav.selectedItemId = R.id.nav_places
        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.nav_events -> {
                    startActivity(Intent(this, HomeScreen::class.java))
                    finish()
                }

                R.id.nav_places -> {

                }

                R.id.nav_shorts -> {

                }

                R.id.nav_updates -> {
                    startActivity(Intent(this, LiveUpdates::class.java))
                    finish()
                }
            }
            true
        }

        binding.menuButton.setOnClickListener {
            binding.main.openDrawer(GravityCompat.START)
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
    }
}
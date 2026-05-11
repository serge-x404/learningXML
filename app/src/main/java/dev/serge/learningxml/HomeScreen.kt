package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import dev.serge.learningxml.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->

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

        val categoryAdapter = listOf(
            Category("Hotel",android.R.drawable.ic_menu_compass),
            Category("Yoga",android.R.drawable.ic_menu_week),
            Category("Music Shows",android.R.drawable.ic_media_play),
            Category("Education",android.R.drawable.btn_star_big_on),
            Category("Sports",android.R.drawable.btn_star),
            Category("More",android.R.drawable.ic_menu_more)
        )
        val adapter = HomeScreenCategoryAdapter(categoryAdapter)
        binding.categoryRecycler.layoutManager = GridLayoutManager(this,3)
        binding.categoryRecycler.adapter = adapter
        binding.categoryRecycler.addItemDecoration(CategoryItemDecoration(4))

        binding.bottomNav.selectedItemId = R.id.nav_events
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_events -> {}
                R.id.nav_places -> {
                    startActivity(Intent(this, LivePlacesScreen::class.java))
                    finish()
                }
                R.id.nav_shorts -> {}
                R.id.nav_updates -> {
                    startActivity(Intent(this, LiveUpdates::class.java))
                    finish()
                }
            }
            true
        }



        binding.menuButton.setOnClickListener {
            Log.i("Button","hello sabjiii")
            binding.drawer.drawerlayout.openDrawer(GravityCompat.START)
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
    }
}
package dev.serge.learningxml

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animate
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import dev.serge.learningxml.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
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
        val adapter = HomeScreenCategoryAdapter(categoryAdapter) {category ->

            val fragment = CategoryFragment()
            val bundle = Bundle()

            bundle.putString(
                "category",
                category.title
            )

            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    fragment
                )
                .addToBackStack(null)
                .commit()
        }
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
        val closeDrawerButton = binding.drawer.customDrawer.findViewById<ImageView>(R.id.closeDrawer)

        closeDrawerButton.setOnClickListener {
            closeDrawer()
        }


        binding.menuButton.setOnClickListener {
            openDrawer()
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
    }

    fun openDrawer() {

        binding.drawer.drawerlayout.animate()
            .translationX(0f)
            .setDuration(250)
            .start()
    }

    fun closeDrawer() {

        binding.drawer.drawerlayout.animate()
            .translationX(-binding.drawer.customDrawer.width.toFloat())
            .setDuration(250)
            .start()
    }
}
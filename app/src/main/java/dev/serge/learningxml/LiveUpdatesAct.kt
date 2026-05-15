package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.serge.learningxml.databinding.ActivityLiveUpdatesBinding

class LiveUpdatesAct : AppCompatActivity() {

    private lateinit var binding: ActivityLiveUpdatesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLiveUpdatesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        val closeDrawer = binding.customDrawer.drawerlayout.findViewById<ImageView>(R.id.closeDrawer)

        closeDrawer.setOnClickListener {
            closeDrawer()
        }

        binding.menuButton.setOnClickListener {
            Log.i("click","you clicked menuButton")
            openDrawer()
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(this,Profile::class.java))
        }

        binding.busTab.setOnClickListener {
            selectedTab(binding.busTab)
        }

        binding.trainTab.setOnClickListener {
            selectedTab(binding.trainTab)
        }

        binding.taxiTab.setOnClickListener {
            selectedTab(binding.taxiTab)
        }
    }
    fun openDrawer() {

        binding.customDrawer.drawerlayout.animate()
            .translationX(0f)
            .setDuration(250)
            .start()
    }

    fun closeDrawer() {

        binding.customDrawer.drawerlayout.animate()
            .translationX(-binding.customDrawer.drawerlayout.width.toFloat())
            .setDuration(250)
            .start()
    }

    fun selectedTab(selectedTab: TextView) {
        val tabs = listOf(
            binding.busTab,
            binding.trainTab,
            binding.taxiTab
        )

        for (tab in tabs) {
            tab.setBackgroundResource(
                R.drawable.tab_unselected
            )
            tab.setTextColor(
                getColor(android.R.color.black)
            )
            selectedTab.setBackgroundResource(
                R.drawable.tab_selected
            )
            selectedTab.setTextColor(
                getColor(android.R.color.white)
            )
        }
    }
}
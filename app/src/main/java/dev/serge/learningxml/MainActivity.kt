package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import dev.serge.learningxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        val navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment

        val navController = navHost.navController

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_events -> {
                    navController.navigate(R.id.eventScreen)
                }
                R.id.nav_places -> {
                    navController.navigate(R.id.livePlaces)
                }
                R.id.nav_shorts -> {
                    navController.navigate(R.id.shorts)
                }
                R.id.nav_updates -> {
                    navController.navigate(R.id.liveUpdates)
                }
            }
            true
        }
        }

//        val fragment = EventScreen()
//
//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.fragmentContainerView,
//                fragment
//            )
//            .addToBackStack(null)
//            .commit()
//
//        binding.bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_events -> {
//                    loadFragment(EventScreen())
//                }
//                R.id.nav_places -> {
//                    loadFragment(LivePlaces())
//                }
//                R.id.nav_shorts -> {
//                    loadFragment(Shorts())
//                }
//                R.id.nav_updates -> {
//                    loadFragment(LiveUpdates())
//                }
//            }
//            true
//        }
//    }
//
//    fun loadFragment(fragment: Fragment) {
//
//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.fragmentContainerView,
//                fragment
//            )
//            .addToBackStack(null)
//            .commit()
//    }
}
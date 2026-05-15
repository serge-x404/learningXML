package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dev.serge.learningxml.databinding.FragmentLivePlacesBinding
import dev.serge.learningxml.databinding.FragmentLiveUpdatesBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LiveUpdates : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentLiveUpdatesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLiveUpdatesBinding.inflate(layoutInflater)
        val root = binding.root

        val closeDrawer = binding.customDrawer.drawerlayout.findViewById<ImageView>(R.id.closeDrawer)

        closeDrawer.setOnClickListener {
            closeDrawer()
        }

        binding.menuButton.setOnClickListener {
            Log.i("click","you clicked menuButton")
            openDrawer()
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(requireContext(),Profile::class.java))
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
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LiveUpdates().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
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
                requireContext().getColor(android.R.color.black)
            )
            selectedTab.setBackgroundResource(
                R.drawable.tab_selected
            )
            selectedTab.setTextColor(
                requireContext().getColor(android.R.color.white)
            )
        }
    }
}
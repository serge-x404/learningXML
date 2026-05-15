package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import dev.serge.learningxml.databinding.FragmentLivePlacesBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LivePlaces : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentLivePlacesBinding

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
        binding = FragmentLivePlacesBinding.inflate(layoutInflater)
        val root = binding.root
        val categories = listOf(
            Category("Hotel",android.R.drawable.ic_menu_compass),
            Category("Shows",android.R.drawable.ic_media_play),
            Category("Sports",android.R.drawable.ic_menu_gallery),
            Category("Yoga",android.R.drawable.ic_menu_manage),
            Category("Education",android.R.drawable.ic_menu_info_details),
            Category("Activities",android.R.drawable.ic_menu_camera),
        )

        val hotelNames = listOf(
            Category("Oberoi", android.R.drawable.ic_menu_compass),
            Category("Taj Resorts", android.R.drawable.ic_menu_compass),
            Category("Grand Hyatt", android.R.drawable.ic_menu_compass),
            Category("Taj Palace", android.R.drawable.ic_menu_compass),
            Category("Vivaan", android.R.drawable.ic_menu_compass),
            Category("More", android.R.drawable.ic_menu_more)
        )

        val adapter = LivePlacesCategoryAdapter(categories) {category ->
            binding.fragmentContainer.visibility = View.VISIBLE
            val fragment = CategoryFragment()
            val bundle = Bundle()

            bundle.putString(
                "category",
                category.title
            )

            fragment.arguments = bundle

            childFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    fragment
                )
                .addToBackStack(null)
                .commit()
        }
        childFragmentManager.addOnBackStackChangedListener {
            if (childFragmentManager.backStackEntryCount == 0) {
                binding.fragmentContainer.visibility = View.GONE
            }
        }

        binding.categoryRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.categoryRecycler.adapter = adapter
        binding.categoryRecycler.addItemDecoration(CategoryItemDecoration(4))

        val hotelAdapter = LivePlacesCategoryAdapter(hotelNames) {category ->

            binding.fragmentContainer.visibility = View.VISIBLE
            val fragment = CategoryFragment()
            val bundle = Bundle()

            bundle.putString(
                "category",
                category.title
            )

            fragment.arguments = bundle

            childFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    fragment
                )
                .addToBackStack(null)
                .commit()
        }
        childFragmentManager.addOnBackStackChangedListener {
            if (childFragmentManager.backStackEntryCount == 0) {
                binding.fragmentContainer.visibility = View.GONE
            }
        }

        binding.categoryRecyclerBottom.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.categoryRecyclerBottom.adapter = hotelAdapter
        binding.categoryRecyclerBottom.addItemDecoration(CategoryItemDecoration(4))

        val closeDrawer = binding.customDrawer.drawerlayout.findViewById<ImageView>(R.id.closeDrawer)

        closeDrawer.setOnClickListener {
            closeDrawer()
        }


        binding.menuButton.setOnClickListener {
            openDrawer()
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(requireContext(), Profile::class.java))
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LivePlaces().apply {
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
}
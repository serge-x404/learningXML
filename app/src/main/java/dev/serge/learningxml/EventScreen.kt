package dev.serge.learningxml

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import dev.serge.learningxml.databinding.FragmentEventScreenBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EventScreen : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentEventScreenBinding

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
        binding = FragmentEventScreenBinding.inflate(layoutInflater)
        val root = binding.root
        val categoryAdapter = listOf(
            Category("Hotel",android.R.drawable.ic_menu_compass),
            Category("Yoga",android.R.drawable.ic_menu_week),
            Category("Music Shows",android.R.drawable.ic_media_play),
            Category("Education",android.R.drawable.btn_star_big_on),
            Category("Sports",android.R.drawable.btn_star),
            Category("More",android.R.drawable.ic_menu_more)
        )
        val adapter = HomeScreenCategoryAdapter(categoryAdapter) { category ->
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

        binding.categoryRecycler.layoutManager = GridLayoutManager(requireContext(),3)
        binding.categoryRecycler.adapter = adapter
        binding.categoryRecycler.addItemDecoration(CategoryItemDecoration(4))

        val closeDrawerButton = binding.drawer.customDrawer.findViewById<ImageView>(R.id.closeDrawer)

        closeDrawerButton.setOnClickListener {
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
            EventScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
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
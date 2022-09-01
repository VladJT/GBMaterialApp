package jt.projects.gbmaterialapp.ui.telescope

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuCompat
import jt.projects.gbmaterialapp.R
import jt.projects.gbmaterialapp.databinding.ActivityBottomNavigationViewBinding
import jt.projects.gbmaterialapp.ui.viewpager.EarthFragment
import jt.projects.gbmaterialapp.ui.viewpager.MarsFragment
import jt.projects.gbmaterialapp.ui.viewpager.WeatherFragment
import jt.projects.gbmaterialapp.util.MARS_FRAGMENT
import jt.projects.gbmaterialapp.util.toast

class BottomNavigationActivity : AppCompatActivity() {

    var counter = 0

    private lateinit var binding: ActivityBottomNavigationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    setBadge(R.id.bottom_view_earth)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, EarthFragment())
                        .commit()
                    true
                }
                R.id.bottom_view_mars -> {
                    setBadge(R.id.bottom_view_mars)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, MarsFragment())
                        .commit()
                    true
                }
                R.id.bottom_view_weather -> {
                    setBadge(R.id.bottom_view_weather)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, WeatherFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_mars

        binding.bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    //Item tapped
                }
                R.id.bottom_view_mars -> {
                    //Item tapped
                }
                R.id.bottom_view_weather -> {
                    //Item tapped
                }
            }
        }
    }

    private fun setBadge(id: Int) {
        binding.bottomNavigationView.removeBadge(R.id.bottom_view_earth)
        binding.bottomNavigationView.removeBadge(R.id.bottom_view_mars)
        binding.bottomNavigationView.removeBadge(R.id.bottom_view_weather)
        binding.bottomNavigationView.getOrCreateBadge(id)
        val badge = binding.bottomNavigationView.getBadge(id)
        badge?.number = counter++

    }
}
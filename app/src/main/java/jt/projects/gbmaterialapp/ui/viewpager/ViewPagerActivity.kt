package jt.projects.gbmaterialapp.ui.viewpager

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import jt.projects.gbmaterialapp.databinding.ActivityViewPagerBinding
import jt.projects.gbmaterialapp.util.EARTH_FRAGMENT
import jt.projects.gbmaterialapp.util.MARS_FRAGMENT
import jt.projects.gbmaterialapp.util.WEATHER_FRAGMENT
import jt.projects.gbmaterialapp.viewmodel.ViewPagerAdapter
import me.relex.circleindicator.CircleIndicator


class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //В activity нам достаточно найти ViewPager в макете, создать адаптер и
        //передать в него SupportFragmentManager. Или ChildFragmentManager, если вы создаёте экраны
        //внутри другого фрагмента
        binding.viewPager.also {
            it.adapter = ViewPagerAdapter(supportFragmentManager)
        }
        binding.indicator.setViewPager(binding.viewPager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.getTabAt(EARTH_FRAGMENT)?.setIcon(android.R.drawable.ic_lock_lock)
        binding.tabLayout.getTabAt(MARS_FRAGMENT)?.setIcon(android.R.drawable.ic_notification_overlay)
        binding.tabLayout.getTabAt(WEATHER_FRAGMENT)?.setIcon(android.R.drawable.ic_menu_recent_history)

        setCustomTabs()
    }


    private fun setCustomTabs() {
//        val layoutInflater = LayoutInflater.from(this)
//        binding.tabLayout.getTabAt(EARTH_FRAGMENT)?.customView =
//            layoutInflater.inflate(, null)
    }

}
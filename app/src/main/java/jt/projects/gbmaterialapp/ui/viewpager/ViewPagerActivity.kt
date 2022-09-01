package jt.projects.gbmaterialapp.ui.viewpager

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import jt.projects.gbmaterialapp.databinding.ActivityViewPagerBinding
import jt.projects.gbmaterialapp.util.EARTH_FRAGMENT
import jt.projects.gbmaterialapp.util.MARS_FRAGMENT
import jt.projects.gbmaterialapp.util.WEATHER_FRAGMENT
import jt.projects.gbmaterialapp.viewmodel.ViewPagerAdapter


class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //В activity нам достаточно найти ViewPager в макете, создать адаптер и
        //передать в него SupportFragmentManager.
        binding.viewPager.adapter = ViewPagerAdapter(this)
        binding.indicator.setViewPager(binding.viewPager)
        setTabs()

    }


    private fun setTabs() {
        //Зато код в Активити заметно упростился. Теперь, чтобы использовать TabView во ViewPager2, вам
        //нужно обратиться к помощи посредника — TabLayoutMediator. Этот класс принимает TabLayout и
        //ViewPager2, а возвращает выбранную пользователем вкладку и ее позицию. Далее мы вкладке (tab)
        //устанавливаем название и иконку. Подсвечивается выбранная вкладка теперь автоматически
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                EARTH_FRAGMENT -> "Earth"
                MARS_FRAGMENT -> "Mars"
                WEATHER_FRAGMENT -> "Weather"
                else -> "Earth"
            }
            tab.icon = when (position) {
                EARTH_FRAGMENT -> ContextCompat.getDrawable(
                    this@ViewPagerActivity,
                    android.R.drawable.ic_lock_lock
                )
                MARS_FRAGMENT -> ContextCompat.getDrawable(
                    this@ViewPagerActivity,
                    android.R.drawable.ic_notification_overlay
                )
                WEATHER_FRAGMENT -> ContextCompat.getDrawable(
                    this@ViewPagerActivity,
                    android.R.drawable.ic_menu_recent_history
                )
                else -> ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_lock_lock)
            }
        }.attach()

    }

}
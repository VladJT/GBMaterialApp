package jt.projects.gbmaterialapp.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import jt.projects.gbmaterialapp.ui.viewpager.EarthFragment
import jt.projects.gbmaterialapp.ui.viewpager.MarsFragment
import jt.projects.gbmaterialapp.ui.viewpager.WeatherFragment
import jt.projects.gbmaterialapp.util.EARTH_FRAGMENT
import jt.projects.gbmaterialapp.util.MARS_FRAGMENT
import jt.projects.gbmaterialapp.util.WEATHER_FRAGMENT


// конструктор мы теперь передаем корневую Активити (или фрагмент), хотя
//можно передавать FragmentManager вместе с Lifecycle, но первый способ предпочтительнее
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), WeatherFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragments[EARTH_FRAGMENT]
            1 -> fragments[MARS_FRAGMENT]
            2 -> fragments[WEATHER_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
    }

}
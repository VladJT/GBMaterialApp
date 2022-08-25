package jt.projects.gbmaterialapp.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import jt.projects.gbmaterialapp.ui.viewpager.EarthFragment
import jt.projects.gbmaterialapp.ui.viewpager.MarsFragment
import jt.projects.gbmaterialapp.ui.viewpager.WeatherFragment

//Адаптер наследуется от класса FragmentStatePagerAdapter. На самом деле есть два вида адаптеров
//для ViewPager: FragmentStatePagerAdapter и FragmentPagerAdapter. Основная разница между ними:
//StateAdapter создаёт и уничтожает фрагменты в процессе перемотки (по аналогии с RecyclerView), а
//простой адаптер держит все фрагменты в памяти.
//Первый способ потребляет мало памяти телефона, но нагружает систему постоянным созданием
//фрагментов. Второй способ не нагружает систему, но потребляет тем больше памяти, чем больше
//фрагментов у вас во ViewPager. То есть если у вас приложение в виде книги или календаря, вам
//имеет смысл создавать новые экраны динамически и уничтожать просмотренные. Если у вас всего
//три экрана, то лучше просто создать их один раз
//И ещё одно замечание: простой адаптер при пролистывании уничтожает только XML-layout, но не
//сам фрагмент (onCreateView/onDestroyView), второй адаптер только хранит
//savedInstanceState. Учитывайте этот нюанс в работе.

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), WeatherFragment())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[EARTH_FRAGMENT]
            1 -> fragments[MARS_FRAGMENT]
            2 -> fragments[WEATHER_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
    }

    companion object {
        private const val EARTH_FRAGMENT = 0
        private const val MARS_FRAGMENT = 1
        private const val WEATHER_FRAGMENT = 2
    }

}
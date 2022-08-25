package jt.projects.gbmaterialapp.ui.viewpager

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import jt.projects.gbmaterialapp.databinding.ActivityViewPagerBinding
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
    }

}
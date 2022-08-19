package jt.projects.gbmaterialapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jt.projects.gbmaterialapp.databinding.ActivityMainBinding
import jt.projects.gbmaterialapp.ui.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_PinkTheme)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}
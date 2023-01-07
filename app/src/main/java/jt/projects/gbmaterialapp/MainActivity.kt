package jt.projects.gbmaterialapp

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import jt.projects.gbmaterialapp.databinding.ActivityMainBinding
import jt.projects.gbmaterialapp.model.SharedPref
import jt.projects.gbmaterialapp.ui.main.PictureOfTheDayFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //@Inject
    //lateinit var cat : Cat

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        super.onCreate(savedInstanceState)
        SharedPref.initSharedPreferencesContext(applicationContext)
        setTheme(SharedPref.getData().theme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }

        //  val car = DaggerCarsComponent.builder().build().getCar()
        // Log.d(TAG, car.getType())
//        var isHideSplashScreen = false
//        object : CountDownTimer(2000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {}
//            override fun onFinish() {
//                isHideSplashScreen = true
//            }
//        }.start()
//        val content: View = findViewById(android.R.id.content)
//        content.viewTreeObserver.addOnPreDrawListener(
//            object : ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw(): Boolean {
//                    return if (isHideSplashScreen) {
//                        content.viewTreeObserver.removeOnPreDrawListener(this)
//                        true
//                    } else {
//                        false
//                    }
//                }
//            })
//
//        splashScreen.setOnExitAnimationListener { splashScreenView ->
//            val slideLeft = ObjectAnimator.ofFloat(
//                splashScreenView,
//                View.TRANSLATION_X,
//                0f,
//                -splashScreenView.height.toFloat()
//            )
//            slideLeft.interpolator = AnticipateInterpolator()
//            slideLeft.duration = 1000L
//            slideLeft.doOnEnd { splashScreenView.remove() }
//            slideLeft.start()
//
//        }
    }

    fun showThemeDialog() {
        val items = resources.getStringArray(R.array.choose_notes_theme)
        // Создаём билдер и передаём контекст приложения
        AlertDialog.Builder(this@MainActivity)
            .setTitle("Выбор темы")
            .setItems(items) { _, which ->
                var newTheme = 0
                when (items[which]) {
                    "Astro (default_theme)" -> newTheme = R.style.GBMaterialApp
                    "Mars (Cut_style)" -> newTheme = R.style.AppTheme_Mars
                    "Mercury (Rounded_style)" -> newTheme = R.style.AppTheme_Neptune
                }
                if (SharedPref.getData().theme != newTheme) {
                    SharedPref.settings.theme = newTheme
                    SharedPref.save()
                    recreate()
                }
            }
            .setNegativeButton("Отмена", null)
            .create().show()
    }
}
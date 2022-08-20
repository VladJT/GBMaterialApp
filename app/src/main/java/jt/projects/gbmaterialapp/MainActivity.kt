package jt.projects.gbmaterialapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import jt.projects.gbmaterialapp.databinding.ActivityMainBinding
import jt.projects.gbmaterialapp.model.SharedPref
import jt.projects.gbmaterialapp.ui.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
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
    }

    fun showThemeDialog() {
        val items = resources.getStringArray(R.array.choose_notes_theme)
        // Создаём билдер и передаём контекст приложения
        AlertDialog.Builder(this@MainActivity)
            .setTitle("Выбор темы")
            .setItems(items) { _, which ->
                var newTheme = 0
                when (which) {
                    0 -> newTheme = R.style.AppTheme_GBMaterialApp
                    1 -> newTheme = R.style.AppTheme_CutTheme
                    2 -> newTheme = R.style.AppTheme_IosTheme
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
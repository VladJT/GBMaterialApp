package jt.projects

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import jt.projects.gbmaterialapp.MainActivity
import jt.projects.gbmaterialapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())
    private val delay = 3000L
    private val rotateValue = 360f
    private val interpolatorDuration = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.imageView.animate().rotationBy(rotateValue)
            .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(interpolatorDuration)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    startActivity(
                        Intent(
                            this@SplashActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })


//        handler.postDelayed({
//            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//            finish()
//        }, delay)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

}
package `in`.tourism.pilgrimagetour.view

import `in`.tourism.pilgrimagetour.databinding.ActivitySplashBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import `in`.tourism.pilgrimagetour.R

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        setContentView(binding.root)
        animation = AnimationUtils.loadAnimation(this, R.anim.splash2image)

        binding.cL1.startAnimation(animation)

        Handler().postDelayed({
            /*binding.mCV1hin.animation = null
            binding.mCV1hin.visibility = View.GONE
            binding.mCV2bud.visibility = View.VISIBLE
            binding.mCV2bud.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash2image))*/
            binding.cL1.animation = null
            binding.mCV1hin.visibility = View.GONE
            binding.mCV2bud.visibility = View.VISIBLE
            binding.cL2.startAnimation(animation)
            /*binding.lL1.animation = null
binding.lL1.visibility = View.GONE
binding.lL2.visibility = View.VISIBLE
binding.lL2.startAnimation(animation)*/
        }, (1 * 1000).toLong())
        Handler().postDelayed({

            /*binding.mCV2bud.animation = null
            binding.mCV2bud.visibility = View.GONE
            binding.mCV3jain.visibility = View.VISIBLE
            binding.mCV3jain.startAnimation(animation)*/
            binding.cL2.animation = null
            binding.mCV2bud.visibility = View.GONE
            binding.mCV3jain.visibility = View.VISIBLE
            binding.cL3.startAnimation(animation)
        }, (2 * 1000).toLong())
        Handler().postDelayed({

            /*binding.mCV3jain.animation = null
            binding.mCV3jain.visibility = View.GONE
            binding.mCV4sik.visibility = View.VISIBLE
            binding.mCV4sik.startAnimation(animation)*/
            binding.cL3.animation = null
            binding.mCV3jain.visibility = View.GONE
            binding.mCV4sik.visibility = View.VISIBLE
            binding.cL4.startAnimation(animation)
        }, (3 * 1000).toLong())
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, (4 * 1000).toLong())
    }
}

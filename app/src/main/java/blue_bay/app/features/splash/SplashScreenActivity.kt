package blue_bay.app.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import blue_bay.app.R
import blue_bay.app.features.menu.SignInMenuActivity

class SplashScreenActivity : AppCompatActivity() {

    protected lateinit var mBinding: blue_bay.app.databinding.ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, SignInMenuActivity::class.java))
            finish()
        }, 1000)

    }
}
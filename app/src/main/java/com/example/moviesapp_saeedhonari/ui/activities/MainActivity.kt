package com.example.moviesapp_saeedhonari.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviesapp_saeedhonari.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        backGroundColor()
        setContentView(R.layout.activity_main)
    }

    fun backGroundColor() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //  window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            // window.navigationBarColor = ContextCompat.getColor(this, R.color.action_bar_color)

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val background = this.resources.getDrawable(R.drawable.bg_action_revers)
            window.statusBarColor = this.resources.getColor(android.R.color.transparent)
            window.navigationBarColor = this.resources.getColor(android.R.color.black)
            window.setBackgroundDrawable(background)
            //  window.navigationBarColor = ContextCompat.getColor(this, R.color.action_bar_color)

        }
    }

   // private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
    /*    //  val fragments = supportFragmentManager.backStackEntryCount
        val fragmentsID = movieNavHostFragment.findNavController().currentDestination?.getId()

        if (fragmentsID!= R.id.descriptionNewsFragment) {
            if (doubleBackToExitPressedOnce) {
                finish()
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
        }else{
            super.onBackPressed()
        }*/
    }
}
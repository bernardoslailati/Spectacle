package com.slailati.android.spectacle.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.ActivityMainBinding
import com.slailati.android.spectacle.ui.base.BaseFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        setContentView(view)
    }

    override fun onBackPressed() {
        val navHostFragment =
            this.supportFragmentManager.findFragmentById(R.id.navHostFragmentContentHome) as? NavHostFragment
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0) as? BaseFragment
        currentFragment?.let {
            if (!it.onBackPressed()) {
                super.onBackPressed()
            }
        }
    }

}
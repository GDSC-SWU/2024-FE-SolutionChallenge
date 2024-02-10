package com.teamfairy.feature

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.teamfairy.core_ui.base.BindingActivity
import com.teamfairy.feature.databinding.ActivityMainBinding


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.fc_main)?.findNavController()!!

        with(binding) {
            navController.let { NavController ->
                botNavMain.setupWithNavController(NavController)
            }
        }
    }
}
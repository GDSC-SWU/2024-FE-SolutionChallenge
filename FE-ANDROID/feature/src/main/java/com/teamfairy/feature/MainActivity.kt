package com.teamfairy.feature

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.teamfairy.core_ui.base.BindingActivity
import com.teamfairy.feature.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                setBottomNaviVisible(navController)
            }
        }
    }

    private fun setBottomNaviVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.botNavMain.visibility =
                if (destination.id in
                    listOf(
                        R.id.navigation_income,
                        R.id.navigation_community,
                        R.id.navigation_consumption,
                        R.id.navigation_my_page,
                        R.id.incomeDetailFragment,
                        R.id.incomeAddFragment
                    )
                ) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

            if (destination.id == R.id.incomeDetailFragment) {
                binding.botNavMain.menu.findItem(R.id.navigation_income).isChecked = true
            }
            if (destination.id == R.id.incomeAddFragment) {
                binding.botNavMain.menu.findItem(R.id.navigation_income).isChecked = true
            }
        }
    }
}
package com.teamfairy.feature.consumption

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.auth.SignInViewModel
import com.teamfairy.feature.databinding.FragmentConsumptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsumptionFragment :
    BindingFragment<FragmentConsumptionBinding>(R.layout.fragment_consumption) {

    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        binding.viewmodel = viewModel
        binding.layoutConsumptionTaxi.tvConsumptionPriceTypeChildren.isVisible = false
        binding.layoutConsumptionTaxi.tvConsumptionPriceChildrenKorea.isVisible = false
        binding.layoutConsumptionTaxi.tvConsumptionPriceChildrenMyCountry.visibility =
            View.INVISIBLE
        binding.layoutConsumptionTaxi.tvConsumptionPriceTypeTeenager.text = "Night"
    }
}
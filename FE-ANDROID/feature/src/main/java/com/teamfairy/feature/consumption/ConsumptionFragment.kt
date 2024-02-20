package com.teamfairy.feature.consumption

import android.view.View
import androidx.core.view.isVisible
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentConsumptionBinding

class ConsumptionFragment :
    BindingFragment<FragmentConsumptionBinding>(R.layout.fragment_consumption) {
    override fun initView() {
        binding.layoutConsumptionTaxi.tvConsumptionPriceTypeChildren.isVisible = false
        binding.layoutConsumptionTaxi.tvConsumptionPriceChildrenKorea.isVisible = false
        binding.layoutConsumptionTaxi.tvConsumptionPriceChildrenMyCountry.visibility = View.INVISIBLE
        binding.layoutConsumptionTaxi.tvConsumptionPriceTypeTeenager.text = "Night"
    }
}
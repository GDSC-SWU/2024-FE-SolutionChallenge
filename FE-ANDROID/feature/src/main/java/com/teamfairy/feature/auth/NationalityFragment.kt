package com.teamfairy.feature.auth

import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.util.fragment.statusBarColorOf
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentNationalityBinding

class NationalityFragment :
    BindingFragment<FragmentNationalityBinding>(R.layout.fragment_nationality) {
    override fun initView() {
        statusBarColorOf(R.color.bg_white)
        setChinaCheckBoxClickListener()
        setVietnamCheckBoxClickListener()
        setCompletionBtnStatus()
        setCompletionClickListener()
    }

    private fun setChinaCheckBoxClickListener() =
        binding.ivNationalityCheckChina.setOnClickListener {
            if (binding.ivNationalityCheckVietnam.isSelected) binding.ivNationalityCheckVietnam.isSelected =
                false
            binding.ivNationalityCheckChina.isSelected = !binding.ivNationalityCheckChina.isSelected
        }

    private fun setVietnamCheckBoxClickListener() =
        binding.ivNationalityCheckVietnam.setOnClickListener {
            if (binding.ivNationalityCheckChina.isSelected) binding.ivNationalityCheckChina.isSelected =
                false
            binding.ivNationalityCheckVietnam.isSelected =
                !binding.ivNationalityCheckVietnam.isSelected
        }

    private fun setCompletionBtnStatus() {
        binding.btnNationality.isSelected =
            (binding.ivNationalityCheckChina.isSelected || binding.ivNationalityCheckVietnam.isSelected)
        binding.btnNationality.isClickable =
            (binding.ivNationalityCheckChina.isSelected || binding.ivNationalityCheckVietnam.isSelected)
    }


    private fun setCompletionClickListener() =
        binding.btnNationality.setOnClickListener {
            findNavController().navigate(R.id.action_nationality_to_income)
        }
}
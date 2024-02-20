package com.teamfairy.feature.auth

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.util.fragment.statusBarColorOf
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentNationalityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class NationalityFragment :
    BindingFragment<FragmentNationalityBinding>(R.layout.fragment_nationality) {
    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        statusBarColorOf(R.color.bg_white)
        binding.ivNationalityCheckChina.isSelected = true
        setChinaCheckBoxClickListener()
        setVietnamCheckBoxClickListener()
        setCompletionClickListener()
        observeNationality()
    }

    private fun setChinaCheckBoxClickListener() =
        binding.ivNationalityCheckChina.setOnClickListener {
            if (binding.ivNationalityCheckVietnam.isSelected) binding.ivNationalityCheckVietnam.isSelected =
                false
            binding.ivNationalityCheckChina.isSelected = true
        }

    private fun setVietnamCheckBoxClickListener() =
        binding.ivNationalityCheckVietnam.setOnClickListener {
            if (binding.ivNationalityCheckChina.isSelected) binding.ivNationalityCheckChina.isSelected =
                false
            binding.ivNationalityCheckVietnam.isSelected = true
        }

    private fun setCompletionClickListener() = binding.btnNationality.setOnClickListener {
        if (binding.ivNationalityCheckChina.isSelected) {
            // viewModel.postNationality("1")
            viewModel.saveCheckLogin(true)
            saveUserInfo(
                "소현",
                "https://lh3.googleusercontent.com/a/ACg8ocKh00ZrLLR3UNLtH5qAgkFLEjNhIoGkFq1OFqmmIMjT=s96-c",
                "1"
            )
        } else {
            // viewModel.postNationality("2")
            saveUserInfo(
                "소현",
                "https://lh3.googleusercontent.com/a/ACg8ocKh00ZrLLR3UNLtH5qAgkFLEjNhIoGkFq1OFqmmIMjT=s96-c",
                "2"
            )
        }
        findNavController().navigate(R.id.action_nationality_to_income)
    }

    private fun observeNationality() {
        viewModel.postNationality.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    viewModel.saveCheckLogin(true)
                    saveUserInfo(it.data.nickname, it.data.profileUrl, it.data.nationality)
                    findNavController().navigate(R.id.action_nationality_to_income)
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun saveUserInfo(
        nickname: String,
        profileUrl: String,
        nationality: String,
    ) {
        viewModel.saveNickName(nickname)
        viewModel.saveMemberProfileUrl(profileUrl)
        viewModel.saveNationality(nationality)
    }
}
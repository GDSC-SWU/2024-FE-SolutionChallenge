package com.teamfairy.feature.mypage

import androidx.fragment.app.viewModels
import coil.load
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.auth.SignInViewModel
import com.teamfairy.feature.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel by viewModels<SignInViewModel>()
    override fun initView() {
        binding.tvMyPageNickname.text = viewModel.getNickName()
        binding.tvMyPageNationality.text =
            "Natoinality : " + if (viewModel.getNationality() == "1") "China" else "Vietnam"
        binding.ivMyPageProfile.load(viewModel.getMemberProfileUrl())
    }
}
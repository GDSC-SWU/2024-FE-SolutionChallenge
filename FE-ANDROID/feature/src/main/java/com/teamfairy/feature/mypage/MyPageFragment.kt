package com.teamfairy.feature.mypage

import androidx.fragment.app.viewModels
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.auth.SignInViewModel
import com.teamfairy.feature.databinding.FragmentMyPageBinding
import com.teamfairy.feature.dialog.DeleteDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel by viewModels<SignInViewModel>()
    override fun initView() {
        binding.viewmodel = viewModel
        binding.tvMyPageNationality.text =
            "Natoinality : " + if (viewModel.getNationality() == "1") "China" else "Vietnam"
        setSignOutClickListener()
    }

    private fun setSignOutClickListener() {
        binding.tvMyPageSignOut.setOnClickListener {
            val dialog = DeleteDialog("Sign Out?", 4)
            dialog.show(childFragmentManager, "delete")
        }
    }
}
package com.teamfairy.feature

import android.os.Handler
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.util.fragment.statusBarColorOf
import com.teamfairy.feature.auth.SignInViewModel
import com.teamfairy.feature.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BindingFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {
    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        statusBarColorOf(R.color.main_color)
        initSplash()
    }

    private fun initSplash() {
        // 타이머가 끝나면 내부 실행
        Handler().postDelayed(
            Runnable {
                when {
                    viewModel.checkLogin() -> {
                        findNavController().navigate(R.id.action_splash_to_income)
                    }

                    else -> {
                        findNavController().navigate(R.id.action_splash_to_signIn)
                    }
                }
            },
            2000,
        )
    }
}
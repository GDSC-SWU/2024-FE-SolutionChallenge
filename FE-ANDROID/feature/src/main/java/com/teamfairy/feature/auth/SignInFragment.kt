package com.teamfairy.feature.auth

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.util.fragment.statusBarColorOf
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.feature.BuildConfig.CLIENT_ID
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SignInFragment : BindingFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {

    private val viewModel by viewModels<SignInViewModel>()

    private lateinit var googleSignResultLauncher: ActivityResultLauncher<Intent>

    override fun initView() {
        statusBarColorOf(R.color.main_color)
        observeLogin()
        binding.btnSignInNavigateMain.isVisible = true
        getGoogleClient()
        googleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }

    private fun getGoogleClient() {
        binding.btnSignInNavigateMain.setOnClickListener {
            val googleSignInOption =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestServerAuthCode(CLIENT_ID)
                    .requestIdToken(CLIENT_ID)
                    .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOption)

            var signIntent: Intent = mGoogleSignInClient.signInIntent
            googleSignResultLauncher.launch(signIntent)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val googleIdToken = account?.idToken.toString()
            if (!googleIdToken.isNullOrBlank()) {
                Timber.tag("idToken").d(googleIdToken)
                findNavController().navigate(R.id.action_signIn_to_nationality)
                //viewModel.postSignIn(googleIdToken)
            }
        } catch (e: ApiException) {
            Timber.d("signInResult:failed Code = " + e.statusCode)
        }
    }

    private fun observeLogin() {
        viewModel.postSignIn.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    saveUserInfo(it.data.accessToken, it.data.refreshToken)
                    findNavController().navigate(R.id.action_signIn_to_nationality)
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun saveUserInfo(
        accessToken: String,
        refreshToken: String,
    ) {
        viewModel.saveAccessToken(accessToken)
        viewModel.saveRefreshToken(refreshToken)
    }
}
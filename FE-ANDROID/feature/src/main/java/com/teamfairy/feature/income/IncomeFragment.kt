package com.teamfairy.feature.income

import android.content.Intent
import android.net.Uri
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.util.fragment.statusBarColorOf
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentIncomeBinding
import com.teamfairy.feature.util.Key.KEY_INCOME_CARD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate

@AndroidEntryPoint
class IncomeFragment : BindingFragment<FragmentIncomeBinding>(R.layout.fragment_income) {
    private val viewModel by viewModels<IncomeViewModel>()

    private lateinit var incomeCardAdapter: IncomeCardAdapter

    override fun initView() {
        statusBarColorOf(R.color.bg_white)
        setBannerClickListener()
        viewModel.postIncomeList()
        navigateToIncomeAddFragment()
        observe()
    }

    private fun initIncomeCardAdapter(data: List<IncomeCardEntity?>) {
        incomeCardAdapter = IncomeCardAdapter(onClickReceiveSalary = { received, id, position ->
            if (!received) {
                navigateToSalaryGuide()
                incomeCardAdapter.deleteItem(position)
                viewModel.deleteIncome(id)
            } else {
                incomeCardAdapter.deleteItem(position)
                viewModel.deleteIncome(id)
            }

        }, onMoveToIncomeDetailClick = { incomeCardData ->
            navigateToIncomeDetailFragment(
                incomeCardData
            )
        }, today = dayOfMonth, countryCode = viewModel.getNationality().toInt()
        ).apply {
            submitList(
                data.ifEmpty {
                    listOf(
                        IncomeCardEntity(
                            -1, "", "40", "0", "0", "0"
                        )
                    )
                }
            )
        }

        binding.rvIncome.adapter = incomeCardAdapter
    }

    private fun navigateToSalaryGuide() {
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://minwon.moel.go.kr/minwonCms/minwonCmsMwmdView/1000.do"),
        )
        startActivity(urlIntent)
    }

    private fun setBannerClickListener() {
        binding.ivIncomeOverdueSalary.setOnClickListener {
            navigateToSalaryGuide()
        }
    }

    private fun navigateToIncomeDetailFragment(incomeCardEntity: IncomeCardEntity) {
        findNavController().navigate(
            R.id.action_income_to_incomeDetail,
            bundleOf(KEY_INCOME_CARD to IncomeCard(incomeCardEntity))
        )
    }

    private fun navigateToIncomeAddFragment() {
        binding.ivIncomeAdd.setOnClickListener {
            findNavController().navigate(
                R.id.action_income_to_incomeAdd
            )
        }
    }

    private fun observe() {
        viewModel.postIncomeList.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    initIncomeCardAdapter(it.data)
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    companion object {
        val dayOfMonth = LocalDate.now().dayOfMonth
    }
}
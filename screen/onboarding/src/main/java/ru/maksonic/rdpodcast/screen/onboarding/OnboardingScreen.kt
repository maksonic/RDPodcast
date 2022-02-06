package ru.maksonic.rdpodcast.screen.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.DebounceClickListener
import ru.maksonic.rdpodcast.screen.onboarding.databinding.ScreenOnboardingBinding

/**
 * @Author: maksonic on 05.02.2022
 */
class OnboardingScreen : BaseFragment<ScreenOnboardingBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenOnboardingBinding =
        ScreenOnboardingBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        with(binding) {
            btnSkipOnboarding.setOnClickListener(clickListener)
            btnShowAuthBottomSheet.setOnClickListener(clickListener)
        }
        initViewPager()
    }

    private val clickListener = object : DebounceClickListener() {
        override fun debounceClick(v: View?) {
            with(binding) {
                when (v?.id) {
                    btnSkipOnboarding.id -> {}
                    btnShowAuthBottomSheet.id -> {}
                }
            }
        }
    }

    private fun initViewPager() {
        with(binding) {
            viewPager.apply {
                adapter = OnboardingAdapter()
                overScrollMode = ViewPager2.OVER_SCROLL_NEVER
                (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }
            TabLayoutMediator(tabLayout, viewPager) { _, _ ->
            }.attach()
        }
    }
}
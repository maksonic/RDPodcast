package ru.maksonic.rdpodcast.screen.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdpodcast.core.base.presentation.BaseFullScreenBottomSheetDialog
import ru.maksonic.rdpodcast.core.ui.DebounceClickListener
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.screen.onboarding.databinding.ScreenOnboardingBinding
import javax.inject.Inject

/**
 * @Author: maksonic on 05.02.2022
 */
@AndroidEntryPoint
class OnboardingScreen : BaseFullScreenBottomSheetDialog<ScreenOnboardingBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenOnboardingBinding =
        ScreenOnboardingBinding::inflate

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetWithoutShadowDialog)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        navigator = Navigator(this)
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
                    btnSkipOnboarding.id -> requireDialog().dismiss()
                    btnShowAuthBottomSheet.id -> navigator.showAuthSheet()
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
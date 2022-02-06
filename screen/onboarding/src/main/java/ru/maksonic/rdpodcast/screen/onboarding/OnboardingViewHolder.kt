package ru.maksonic.rdpodcast.screen.onboarding

import ru.maksonic.rdpodcast.core.base.presentation.BaseViewHolder
import ru.maksonic.rdpodcast.screen.onboarding.databinding.ItemOnboardingBinding

/**
 * @Author: maksonic on 05.02.2022
 */

class OnboardingViewHolder(
    private val binding: ItemOnboardingBinding
    ) : BaseViewHolder<OnboardingUi, ItemOnboardingBinding>(binding) {
    override fun bind(item: OnboardingUi) {
        with(binding) {
            title.text = item.title
            description.text = item.description
            image.setImageResource(item.image)
        }
    }
}
package ru.maksonic.rdpodcast.screen.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.screen.categories.databinding.ScreenCategoriesBinding

/**
 * @Author: maksonic on 06.02.2022
 */
@AndroidEntryPoint
class CategoriesScreen : BaseFragment<ScreenCategoriesBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenCategoriesBinding =
        ScreenCategoriesBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

    }
}
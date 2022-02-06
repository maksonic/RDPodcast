package ru.maksonic.rdpodcast.screen.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.screen.collections.databinding.ScreenCollectionsBinding

/**
 * @Author: maksonic on 06.02.2022
 */
@AndroidEntryPoint
class CollectionsScreen : BaseFragment<ScreenCollectionsBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenCollectionsBinding
        get() = ScreenCollectionsBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
     //   initViewPager(this)
    }

   /* private fun initViewPager(fragment: Fragment) {
        with(binding) {
            viewPager.adapter = CollectionsPagerAdapter(fragment)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getTabTitle(position)
            }.attach()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            FAVORITES_PAGE_INDEX -> getString(R.string.tab_favorites)
            DOWNLOADS_PAGE_INDEX -> getString(R.string.tab_downloads)
            else -> null
        }
    }*/
}


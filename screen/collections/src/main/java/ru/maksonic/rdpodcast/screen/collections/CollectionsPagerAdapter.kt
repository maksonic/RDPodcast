package ru.maksonic.rdpodcast.screen.collections

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.maksonic.rdpodcast.feature.podcast.DownloadsScreen
import ru.maksonic.rdpodcast.feature.podcast.FavoritesScreen


/**
 * @Author: maksonic on 06.02.2022
 */
const val FAVORITES_PAGE_INDEX = 0
const val DOWNLOADS_PAGE_INDEX = 1

class CollectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        FAVORITES_PAGE_INDEX to { FavoritesScreen() },
        DOWNLOADS_PAGE_INDEX to { DownloadsScreen() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}

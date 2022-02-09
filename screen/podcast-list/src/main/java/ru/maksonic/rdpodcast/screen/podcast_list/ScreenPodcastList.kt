package ru.maksonic.rdpodcast.screen.podcast_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.Player
import ru.maksonic.rdpodcast.core.ui.ToolBarBehavior
import ru.maksonic.rdpodcast.feature.podcast.PodcastActionBottomSheet
import ru.maksonic.rdpodcast.feature.podcast.PodcastAdapter
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.navigation.api.navigationData
import ru.maksonic.rdpodcast.screen.podcast_list.databinding.ScreenPodcastListBinding
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi
import javax.inject.Inject

/**
 * @Author: maksonic on 08.02.2022
 */

class ScreenPodcastList : BaseFragment<ScreenPodcastListBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenPodcastListBinding =
        ScreenPodcastListBinding::inflate

    @Inject
    lateinit var navigator: Navigator

    private var _adapter: PodcastAdapter? = null
    private val adapter: PodcastAdapter
        get() = _adapter!!

    override fun prepareView(savedInstanceState: Bundle?) {
        (activity as ToolBarBehavior).hideMainToolbar()
        navigator = Navigator(this)
        val data = navigationData as? CategoryUi
        val title = data?.name
        binding.toolBar.initToolbar(title, fragment = this)
        initRecyclerAdapter()
    }

    private fun initRecyclerAdapter() {

        val list = mutableListOf(
            PodcastUi(0, 0, "1"),
            PodcastUi(0, 0, "1"),
            PodcastUi(0, 0, "1"),
            PodcastUi(1, 1, "2"),
            PodcastUi(1, 1, "2"),
            PodcastUi(1, 1, "2"),
            PodcastUi(1, 1, "2"),
            PodcastUi(1, 1, "2"),
            PodcastUi(1, 1, "2"),
            PodcastUi(1, 1, "2"),
            PodcastUi(2, 2, "3"),
            PodcastUi(2, 2, "3"),
            PodcastUi(2, 2, "3"),
            PodcastUi(2, 2, "3"),
            PodcastUi(2, 2, "3"),
            PodcastUi(2, 2, "3"),
            PodcastUi(2, 2, "3"),
        )

        _adapter = PodcastAdapter(
            onClick = { (activity as Player).playClicked() },
            onMoreClick = {
                PodcastActionBottomSheet().show(
                    childFragmentManager,
                    PodcastActionBottomSheet.TAG
                )
            }
        )
        binding.podcastRecyclerView.adapter = _adapter
        adapter.submitList(list)
    }


    override fun onDestroy() {
        super.onDestroy()
        (activity as ToolBarBehavior).showMainToolbar()
    }
}
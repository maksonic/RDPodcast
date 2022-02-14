package ru.maksonic.rdpodcast.screen.podcast_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.base.presentation.Delay
import ru.maksonic.rdpodcast.core.ui.Player
import ru.maksonic.rdpodcast.core.ui.ToolBarBehavior
import ru.maksonic.rdpodcast.feature.podcast.PodcastAdapter
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.navigation.api.navigationData
import ru.maksonic.rdpodcast.screen.podcast_list.databinding.ScreenPodcastListBinding
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.gone
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.visible
import javax.inject.Inject

/**
 * @Author: maksonic on 08.02.2022
 */
@AndroidEntryPoint
class ScreenPodcastList : BaseFragment<ScreenPodcastListBinding>(), PodcastListFeature.Render {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenPodcastListBinding =
        ScreenPodcastListBinding::inflate

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var imageLoader: RequestManager

    private var _adapter: PodcastAdapter? = null
    private val adapter: PodcastAdapter
        get() = requireNotNull(_adapter)

    private var _passedData: CategoryUi? = null
    private val passedData: CategoryUi
        get() = requireNotNull(_passedData)

    private val viewModel: PodcastListViewModel by viewModels()

    override fun prepareView(savedInstanceState: Bundle?) {
        (activity as ToolBarBehavior).hideMainToolbar()
        _passedData = navigationData as? CategoryUi
        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state -> render(state) }
        }
        fetchDataAction()
        initHeader()
        initRecyclerAdapter()
        initSwipeRefreshLayout(passedData.categoryId)
    }

    override fun initHeader() {
        with(binding) {
            categoryName.text = passedData.name
            toolBar.title = passedData.name
            imageLoader.load(passedData.image).into(imgCategory)
        }
    }

    override fun initRecyclerAdapter() {
        _adapter = PodcastAdapter(
            imageLoader,
            onClick = {
                (activity as Player).playClicked()
                (activity as Player).setPodcastInfo(
                    categoryName = passedData.name,
                    podcastName = it?.name,
                    podcastImg = it?.image
                )
            },
            onMoreClick = {
                navigator.showPodcastAction(it)
            }
        )
        binding.podcastRecyclerView.adapter = _adapter
    }

    override fun initSwipeRefreshLayout(category: String?) {
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                lifecycleScope.launch {
                    delay(Delay.baseRequestDelay)
                    refreshDataAction(category)
                }
            }
        }
    }

    override fun render(state: PodcastListFeature.State) {
        when (state) {
            is PodcastListFeature.State.Loading -> renderLoadingState(state)
            is PodcastListFeature.State.Fetched -> renderFetchedState(state)
            is PodcastListFeature.State.Refresh -> renderRefreshState(state)
            is PodcastListFeature.State.Refreshed -> renderRefreshedState(state)
            is PodcastListFeature.State.Error -> renderErrorState(state)
        }
    }

    override fun renderLoadingState(state: PodcastListFeature.State.Loading) {
        binding.loadingViewState.show()
    }

    override fun renderFetchedState(state: PodcastListFeature.State.Fetched) {
        with(binding) {
            errorState.hide()
            loadingViewState.hide()
            swipeRefreshLayout.isRefreshing = false
            podcastRecyclerView.visible(false)
            adapter.apply {
                submitList(state.fetchedPodcasts)
            }
            numberOfPodcasts.text = resources.getQuantityString(
                R.plurals.podcast_count_hint,
                state.podcastCount,
                state.podcastCount
            )
        }
    }

    override fun renderRefreshState(state: PodcastListFeature.State.Refresh) {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun renderRefreshedState(state: PodcastListFeature.State.Refreshed) {
        with(binding) {
            errorState.hide()
            loadingViewState.hide()
            swipeRefreshLayout.isRefreshing = false
            podcastRecyclerView.visible(false)
            adapter.apply {
                submitList(state.refreshedCategories)
            }
            numberOfPodcasts.text = resources.getQuantityString(
                R.plurals.podcast_count_hint,
                state.podcastCount,
                state.podcastCount
            )
        }
    }

    override fun renderErrorState(state: PodcastListFeature.State.Error) {
        with(binding) {
            loadingViewState.hide()
            swipeRefreshLayout.isRefreshing = false
            podcastRecyclerView.gone(false)
            errorState.apply {
                show()
                acceptPressed { fetchDataAction() }
                setErrorMessage(state.message)
                setErrorEmoji(getString(R.string.state_error_emoji))
            }
        }
    }

    override fun fetchDataAction() = viewModel.dispatch(
        PodcastListFeature.Msg.Ui.FetchPodcasts(passedData.categoryId)
    )

    override fun refreshDataAction(category: String?) =
        viewModel.dispatch(PodcastListFeature.Msg.Ui.RefreshPodcasts(category))

    override fun onDestroy() {
        super.onDestroy()
        (activity as ToolBarBehavior).showMainToolbar()
    }
}
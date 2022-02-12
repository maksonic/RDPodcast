package ru.maksonic.rdpodcast.screen.podcast_list

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.data.Delay
import ru.maksonic.rdpodcast.core.ui.Player
import ru.maksonic.rdpodcast.core.ui.ToolBarBehavior
import ru.maksonic.rdpodcast.feature.podcast.PodcastActionBottomSheet
import ru.maksonic.rdpodcast.feature.podcast.PodcastAdapter
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.navigation.api.navigationData
import ru.maksonic.rdpodcast.screen.podcast_list.databinding.ScreenPodcastListBinding
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi
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
        initRecyclerAdapter()
        initSwipeRefreshLayout(passedData.categoryId)
        viewModel.dispatch(PodcastListFeature.Msg.Ui.FetchPodcasts(passedData.categoryId))
     //   viewModel.dispatch(PodcastListFeature.Msg.Ui.InitScreenHeader(this))
    }

    override fun render(state: PodcastListFeature.State) {
        when (state) {
            is PodcastListFeature.State.Loading -> renderLoadingState(state)
            is PodcastListFeature.State.Fetched -> renderFetchedState(state)
            is PodcastListFeature.State.Refresh -> renderRefreshState(state)
            is PodcastListFeature.State.Refreshed -> renderRefreshedState(state)
            is PodcastListFeature.State.Error -> renderErrorState(state)
            is PodcastListFeature.State.ScreenHeader -> initScreenHeader(state)
        }
    }

    override fun initRecyclerAdapter() {
        _adapter = PodcastAdapter(
            onClick = {
                (activity as Player).playClicked()
            },
            onMoreClick = {
                PodcastActionBottomSheet().show(
                    childFragmentManager,
                    PodcastActionBottomSheet.TAG
                )
            }
        )
        binding.podcastRecyclerView.adapter = _adapter
    }


    override fun renderLoadingState(state: PodcastListFeature.State.Loading) {
        binding.loadingViewState.show()
    }

    override fun renderFetchedState(state: PodcastListFeature.State.Fetched) {
        with(binding) {
            errorState.hide()
            loadingViewState.hide()
            swipeRefreshLayout.isRefreshing = false
            adapter.apply {
                submitList(state.fetchedPodcasts)
            }
            podcastRecyclerView.visible(false)

            binding.numberOfPodcasts.text = resources.getQuantityString(
                R.plurals.podcast_count_hint,
                state.fetchedPodcasts.count(),
                state.fetchedPodcasts.count()
            )
        }
    }

    override fun renderRefreshState(state: PodcastListFeature.State.Refresh) {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun renderRefreshedState(state: PodcastListFeature.State.Refreshed) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.podcastRecyclerView.visible(false)
        adapter.apply {
            submitList(state.refreshedCategories)
        }
    }

    override fun renderErrorState(state: PodcastListFeature.State.Error) {
        with(binding) {
            loadingViewState.hide()
            swipeRefreshLayout.isRefreshing = false
            errorState.show()
            errorState.setErrorMessage(state.message)
            errorState.setErrorEmoji(getString(R.string.state_error_emoji))
            errorState.acceptPressed {
                viewModel.dispatch(PodcastListFeature.Msg.Ui.FetchPodcasts(passedData.categoryId!!))
            }
            podcastRecyclerView.gone(false)

        }
    }

    private fun initScreenHeader(state: PodcastListFeature.State.ScreenHeader) {
        with(binding) {
            categoryName.text = state.categoryName
            toolBar.title = state.categoryName
            Glide.with(imgCategory).load(state.categoryImg).into(imgCategory)
        }

    }

    override fun initSwipeRefreshLayout(category: String?) {
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                lifecycleScope.launch {
                    delay(Delay.baseRequestDelay)
                    viewModel.dispatch(PodcastListFeature.Msg.Ui.RefreshPodcasts(category))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as ToolBarBehavior).showMainToolbar()
    }
}
package ru.maksonic.rdpodcast.screen.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.Player
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.screen.categories.adapter.CategoryAdapter
import ru.maksonic.rdpodcast.screen.categories.databinding.ScreenCategoriesBinding
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.gone
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.visible
import javax.inject.Inject

/**
 * @Author: maksonic on 06.02.2022
 */
@AndroidEntryPoint
class CategoriesScreen @Inject constructor() : BaseFragment<ScreenCategoriesBinding>(),
    CategoriesFeature.Render {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenCategoriesBinding
        get() = ScreenCategoriesBinding::inflate

    private val viewModel: CategoriesViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator
    private var _adapter: CategoryAdapter? = null
    private val adapter: CategoryAdapter
        get() = _adapter!!

    override fun prepareView(savedInstanceState: Bundle?) {

        navigator = Navigator(this)
        initRecyclerAdapter()
        initSwipeRefreshLayout()
        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state -> render(state) }
        }
    }

    override fun initRecyclerAdapter() {
        _adapter = CategoryAdapter { category ->
            navigator.toCategoryPodcastList(category)
        }
        binding.categoriesRecyclerView.adapter = adapter
    }

    override fun render(state: CategoriesFeature.State) {
        when (state) {
            is CategoriesFeature.State.Loading -> renderLoadingState(state)
            is CategoriesFeature.State.Fetched -> renderFetchedState(state)
            is CategoriesFeature.State.Refresh -> renderRefreshState(state)
            is CategoriesFeature.State.Refreshed -> renderRefreshedState(state)
            is CategoriesFeature.State.Error -> renderErrorState(state)
        }
    }

    override fun renderLoadingState(state: CategoriesFeature.State.Loading) {
        with(binding) {
            loadingViewState.show()
        }
    }

    override fun renderFetchedState(state: CategoriesFeature.State.Fetched) {
        with(binding) {
            errorState.hide()
            loadingViewState.hide()
            swipeRefreshLayout.isRefreshing = false
            adapter.apply {
                submitList(state.fetchedCategories)
            }
            categoriesRecyclerView.visible(false)
        }
    }

    override fun renderRefreshState(state: CategoriesFeature.State.Refresh) {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun renderRefreshedState(state: CategoriesFeature.State.Refreshed) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.categoriesRecyclerView.visible(false)
        adapter.apply {
            submitList(state.refreshedCategories)
        }
    }

    override fun renderErrorState(state: CategoriesFeature.State.Error) {
        with(binding) {
            loadingViewState.hide()
            swipeRefreshLayout.isRefreshing = false
            errorState.show()
            errorState.setErrorMessage(state.message)
            errorState.acceptPressed {
                viewModel.dispatch(CategoriesFeature.Msg.Ui.FetchCategories)
            }
            appBarLayout.setExpanded(true)
            categoriesRecyclerView.gone(false)

        }
    }

    private fun initSwipeRefreshLayout() {
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                lifecycleScope.launch {
                    viewModel.dispatch(CategoriesFeature.Msg.Ui.RefreshCategories)
                }
            }
        }
    }
}


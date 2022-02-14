package ru.maksonic.rdpodcast.screen.categories

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
class CategoriesScreen : BaseFragment<ScreenCategoriesBinding>(),
    CategoriesFeature.Render {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenCategoriesBinding
        get() = ScreenCategoriesBinding::inflate

    private val viewModel: CategoriesViewModel by viewModels()
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var imageLoader: RequestManager

    private var _adapter: CategoryAdapter? = null
    private val adapter: CategoryAdapter
        get() = requireNotNull(_adapter)

    override fun prepareView(savedInstanceState: Bundle?) {
        initRecyclerAdapter()
        initSwipeRefreshLayout()
        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state -> render(state) }
        }
    }

    override fun initRecyclerAdapter() {
        _adapter = CategoryAdapter(
            onCategoryClicked = { category ->
                viewModel.dispatch(CategoriesFeature.Msg.Ui.OnCategoryClicked(navigator, category))
            },
            imageLoader
        )
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
        binding.loadingViewState.show()
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
            categoriesRecyclerView.gone(false)
            appBarLayout.setExpanded(true)
            errorState.apply {
                show()
                setErrorMessage(state.message)
                acceptPressed { viewModel.dispatch(CategoriesFeature.Msg.Ui.FetchCategories) }
            }
        }
    }

    override fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                delay(Delay.baseRequestDelay)
                viewModel.dispatch(CategoriesFeature.Msg.Ui.RefreshCategories)
            }
        }
    }
}


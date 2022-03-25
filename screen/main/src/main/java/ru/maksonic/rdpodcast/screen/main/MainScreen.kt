package ru.maksonic.rdpodcast.screen.main

import android.os.Bundle
import android.util.TypedValue
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maksonic.rdpodcast.core.BottomSheetStateBackPressed
import ru.maksonic.rdpodcast.core.PlayerSheetStateListener
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.*
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.screen.main.databinding.ScreenMainBinding
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.gone
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.visible
import javax.inject.Inject

/**
 * @Author: maksonic on 06.02.2022
 */
@AndroidEntryPoint
class MainScreen : BaseFragment<ScreenMainBinding>(), Player, BottomSheetStateBackPressed {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenMainBinding
        get() = ScreenMainBinding::inflate

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var imageLoader: RequestManager

    @Inject
    lateinit var playerBottomSheetStateListener: PlayerSheetStateListener

    private var _navController: NavController? = null
    private val navController: NavController
        get() = requireNotNull(_navController)

    private var _bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private val bottomSheetBehavior: BottomSheetBehavior<*>
        get() = requireNotNull(_bottomSheetBehavior)

    private var _shownPlayerPadding: Int? = null
    private val shownPlayerPadding: Int
        get() = requireNotNull(_shownPlayerPadding)

    private var _hiddenPlayerPadding: Int? = null
    private val hiddenPlayerPadding: Int
        get() = requireNotNull(_hiddenPlayerPadding)


    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        initNavController()
        initBottomNavigation()
        setUpToolbar()
        calculatePaddingValue()
        binding.mainGraphContainer.setPadding(0, 0, 0, shownPlayerPadding)
        initBottomSheet()
        playerBottomSheetStateListener.listenState(bottomSheetBehavior)
        with(binding) {
            playerBottomSheet.clickableView.setOnClickListener(clickListener)
        }
        mergeBottomSheetBehaviorWithMotionTransition()
        setUpBottomPlayerActions()
        setPodcastInfo()
    }

    private fun setUpBottomPlayerActions() {
        with(binding) {
            val containerActions = playerBottomSheet.containerActions
            playerBottomSheet.btnMenu.setOnClickListener {
                if (containerActions.isVisible) {
                    containerActions.gone(true)
                } else {
                    containerActions.visible(true)
                }
            }
            playerBottomSheet.btnSharePodcast.setOnClickListener {
                containerActions.gone(true)
            }
            playerBottomSheet.btnDownloadPodcast.setOnClickListener {
                containerActions.gone(true)
            }
            playerBottomSheet.btnFavoritePodcast.setOnClickListener {
                containerActions.gone(true)
            }
        }
    }

    private fun setUpToolbar() {
        binding.toolBarMain.setNavigationOnClickListener {
            navigator.mainToUserProfile()
        }
        binding.toolBarMain.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    navigator.mainToSettings()
                    true
                }
                else -> false
            }
        }
    }

    private fun initBottomSheet() {
        _bottomSheetBehavior = BottomSheetBehavior.from(binding.playerBottomSheet.bottomSheet)
        bottomSheetBehavior.setPeekHeight(shownPlayerPadding, true)
        bottomSheetBehavior.setStateHidden()
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            binding.mainGraphContainer.setPadding(0, 0, 0, hiddenPlayerPadding)
        }
    }

    private fun initNavController() {
        val host =
            childFragmentManager.findFragmentById(R.id.mainGraphContainer) as NavHostFragment
        _navController = host.navController
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_graph -> {
                    navController.setGraph(R.navigation.home_graph)
                    return@setOnItemSelectedListener true
                }
                R.id.categories_graph -> {
                    navController.setGraph(R.navigation.categories_graph)
                    return@setOnItemSelectedListener true
                }
                R.id.collection_graph -> {
                    navController.setGraph(R.navigation.collection_graph)
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    /**
     * @property [shownPlayerPadding] is used for the bottom padding of the nav container
     * and for the peekHeight of bottom sheet behavior.
     * @property [hiddenPlayerPadding] is used for the bottom padding of the nav container
     * when the bottom sheet is in the hidden state. */
    private fun calculatePaddingValue() {
        val tv = TypedValue()
        val collapsedPlayerHeight = resources.getDimension(R.dimen.player_collapsed)
        if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)
        ) {
            val bottomNavHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
            _shownPlayerPadding = collapsedPlayerHeight.plus(bottomNavHeight).toInt()
            _hiddenPlayerPadding = bottomNavHeight
        }
    }

    private fun mergeBottomSheetBehaviorWithMotionTransition() {
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        with(binding) {
                            mainScreen.transitionToEnd()
                            playerBottomSheet.bottomSheet.transitionToEnd()
                        }
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        with(binding) {
                            mainScreen.transitionToStart()
                            playerBottomSheet.bottomSheet.transitionToStart()
                            mainGraphContainer.setPadding(0, 0, 0, shownPlayerPadding)
                        }
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.mainGraphContainer.setPadding(0, 0, 0, hiddenPlayerPadding)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                with(binding) {
                    mainScreen.progress = slideOffset
                    playerBottomSheet.bottomSheet.progress = slideOffset
                    if (slideOffset < 0) {
                        mainGraphContainer.setPadding(0, 0, 0, hiddenPlayerPadding)
                    }
                }
            }
        })
    }

    override fun setPodcastInfo() {
        with(binding) {
            lifecycleScope.launch {
                val podcast = viewModel.currentPodcast()
                imageLoader.load(podcast?.image).override(Target.SIZE_ORIGINAL)
                    .into(playerBottomSheet.imgPodcast)
                playerBottomSheet.apply {
                  //  txtPodcastCategory.text = categoryName
                    txtPodcastNameCollapsed.isSelected = true
                    txtPodcastNameExpanded.text = podcast?.name
                    txtPodcastNameExpanded.isSelected = true
                }
            }

           // viewModel.setPodcastName(podcastName!!)
        }
        lifecycleScope.launch {
            viewModel.uiPodcastName.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { podcast ->
                    binding.playerBottomSheet.txtPodcastNameCollapsed.text = podcast
                }

        }
    }

    override fun playClicked() {
        bottomSheetBehavior.setStateExpanded()
    }

    private val clickListener = object : DebounceClickListener() {
        override fun debounceClick(v: View?) {
            with(binding) {
                when (v?.id) {
                    playerBottomSheet.clickableView.id -> bottomSheetBehavior.setStateExpanded()
                }
            }
        }
    }
    override val bottomSheet: BottomSheetBehavior<*> get() = bottomSheetBehavior
}
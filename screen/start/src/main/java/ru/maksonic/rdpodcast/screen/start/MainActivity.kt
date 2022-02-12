package ru.maksonic.rdpodcast.screen.start

import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maksonic.rdpodcast.core.ui.DebounceClickListener
import ru.maksonic.rdpodcast.core.ui.ToolBarBehavior
import ru.maksonic.rdpodcast.screen.start.databinding.ActivityMainBinding
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.gone
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.visible

import ru.maksonic.rdpodcast.core.ui.Player


/**
 * @Author: maksonic on 05.02.2022
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolBarBehavior, Player {

    private lateinit var binding: ActivityMainBinding
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

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        initGraphDestination(navController)
        calculatePaddingValue()
        with(binding) {
            playerBottomSheet.clickableView.setOnClickListener(clickListener)
            _bottomSheetBehavior = BottomSheetBehavior.from(playerBottomSheet.bottomSheet)
            bottomSheetBehavior.setPeekHeight(shownPlayerPadding, true)
            bottomNavigation.setupWithNavController(navController)
            navGraphContainer.setPadding(0, 0, 0, shownPlayerPadding)

        }
        mergeBottomSheetBehaviorWithMotionTransition()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun initNavController() {
        val host =
            supportFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment
        _navController = host.navController
    }

    private fun initGraphDestination(navController: NavController) {
        lifecycleScope.launch {
            viewModel.userAuthState(navController)
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
        if (this.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)
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
                            navGraphContainer.setPadding(0, 0, 0, shownPlayerPadding)
                        }
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                with(binding) {
                    mainScreen.progress = slideOffset
                    playerBottomSheet.bottomSheet.progress = slideOffset
                    if (slideOffset < 0) {
                        navGraphContainer.setPadding(0, 0, 0, hiddenPlayerPadding)
                    }
                }
            }
        })
    }

    override fun playClicked() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private val clickListener = object : DebounceClickListener() {
        override fun debounceClick(v: View?) {
            with(binding) {
                when (v?.id) {
                    playerBottomSheet.clickableView.id -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            super.onBackPressed()
        }
    }

    override fun showMainToolbar() {
        binding.appBarLayout.visible(false)
    }

    override fun hideMainToolbar() {
        binding.appBarLayout.gone(false)
    }
}
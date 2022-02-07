package ru.maksonic.rdpodcast.screen.start

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.maksonic.rdpodcast.core.ui.DebounceClickListener
import ru.maksonic.rdpodcast.screen.start.databinding.ActivityMainBinding

/**
 * @Author: maksonic on 05.02.2022
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var _navController: NavController? = null
    private val navController: NavController
        get() = requireNotNull(_navController)

    private var _bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private val bottomSheetBehavior: BottomSheetBehavior<*>
        get() = requireNotNull(_bottomSheetBehavior)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        initGraphDestination(navController)
        with(binding) {
            playerBottomSheet.clickableView.setOnClickListener(clickListener)
            _bottomSheetBehavior = BottomSheetBehavior.from(playerBottomSheet.bottomSheet)
            bottomNavigation.setupWithNavController(navController)
        }
        setMarginBottomSheet()
        mergeBottomSheetBehaviorWithMotionTransition()

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

    /* 72 is the height of the visible part of the bottom sheet.
    In order for the sheet to display correctly, you need to add the height of the bottom nav
    to visible part height. We assume that the height of the bottom nav is equal to the AppBar */
    private fun setMarginBottomSheet() {
        val tv = TypedValue()
        if (this.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)
        ) {
            val marginBottom =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
                    .plus((72 * resources.displayMetrics.density).toInt())
            bottomSheetBehavior.setPeekHeight(marginBottom, true)
        }
    }

    private fun mergeBottomSheetBehaviorWithMotionTransition() {
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.mainScreen.transitionToEnd()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.mainScreen.transitionToStart()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                with(binding) {
                    mainScreen.progress = slideOffset
                    binding.playerBottomSheet.bottomSheet.progress = slideOffset

                }
            }
        })
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
}
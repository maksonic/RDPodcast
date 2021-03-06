package ru.maksonic.rdpodcast.screen.start

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maksonic.rdpodcast.core.BottomSheetStateBackPressed
import ru.maksonic.rdpodcast.core.ui.*
import ru.maksonic.rdpodcast.screen.start.databinding.ActivityMainBinding

/**
 * @Author: maksonic on 05.02.2022
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _navController: NavController? = null
    private val navController: NavController
        get() = requireNotNull(_navController)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        initStartDestination(navController)
    }

    private fun initNavController() {
        val host =
            supportFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment
        _navController = host.navController
        val inflater = host.navController.navInflater
        val graph = inflater.inflate(R.navigation.global_graph)
        host.navController.graph = graph
    }

    private fun initStartDestination(navController: NavController) {
        lifecycleScope.launch {
            viewModel.userAuthState(navController)
        }
    }

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment
        val currentFragment =
            navHostFragment.childFragmentManager.fragments[0] as? BottomSheetStateBackPressed
        if (currentFragment != null) {
            if (currentFragment.bottomSheet.state == BottomSheetBehavior.STATE_EXPANDED) {
                currentFragment.bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}
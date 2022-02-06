package ru.maksonic.rdpodcast.screen.start

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        initGraphDestination(navController)
        binding.bottomNavigation.setupWithNavController(navController)
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
}
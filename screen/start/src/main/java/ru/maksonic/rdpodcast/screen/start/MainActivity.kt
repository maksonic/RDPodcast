package ru.maksonic.rdpodcast.screen.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.NavHostFragment
import ru.maksonic.rdpodcast.screen.onboarding.OnboardingScreen
import ru.maksonic.rdpodcast.screen.start.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
       /* if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<OnboardingScreen>(R.id.overGraphContainer)
            }

        }*/
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph =
            navController.navInflater.inflate(R.navigation.start_graph)
        /* If user not authorized navigation graph set start onboarding screen
           else the user is taken to the home screen.*/
        navController.graph = navGraph
    }
}
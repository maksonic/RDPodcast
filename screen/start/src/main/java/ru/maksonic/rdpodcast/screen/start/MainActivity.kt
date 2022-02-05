package ru.maksonic.rdpodcast.screen.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import ru.maksonic.rdpodcast.screen.start.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph =
            navController.navInflater.inflate(R.navigation.global_graph)
        /* If user not authorized navigation graph set start onboarding screen
           else the user is taken to the home screen.*/
        navGraph.setStartDestination(R.id.onboardingScreen)
        navController.graph = navGraph

        if (navController.currentDestination?.id == R.id.onboardingScreen) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }

    }
}
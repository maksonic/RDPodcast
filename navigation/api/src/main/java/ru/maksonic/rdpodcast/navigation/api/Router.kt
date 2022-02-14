package ru.maksonic.rdpodcast.navigation.api

import android.os.Parcelable
import androidx.fragment.app.Fragment
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi
import javax.inject.Inject

/**
 * @Author: maksonic on 06.02.2022
 */
typealias Navigator = Router.Base

interface Router {
    fun showOnboarding()
    fun showAuthSheet()
    // Auth
    fun authToPrivacy(data: Parcelable?)
    fun authToSignUpUsername()
    fun authToLogIn()
    //Categories
    fun toCategoryPodcastList(category: CategoryUi?)
    fun showPodcastAction(podcast: PodcastUi?)

    class Base @Inject constructor(val fragment: Fragment) : Router {
        override fun showOnboarding() {
            fragment.navigate(R.id.action_mainScreen_to_onboarding_graph)
        }

        override fun showAuthSheet() {
            fragment.navigate(R.id.action_onboardingScreen_to_auth_graph)
        }

        /* AUTH SCREEN FLOW */
        override fun authToPrivacy(data: Parcelable?) {
            fragment.navigate(R.id.action_authBottomSheetDialog_to_screenPrivacyPolicy, data = data)
        }

        override fun authToLogIn() {
            //    fragment.navigate(R.id.action_authBottomSheetDialog_to_logInScreen)
        }

        override fun authToSignUpUsername() {
            // fragment.navigate(R.id.action_authBottomSheetDialog_to_signUpUsernameScreen)
        }

        override fun toCategoryPodcastList(category: CategoryUi?) {
            fragment.navigate(R.id.action_categoriesScreen_to_screenPodcastList, data = category)
        }

        override fun showPodcastAction(podcast: PodcastUi?) {
            fragment.navigate(R.id.action_screenPodcastList_to_podcastActionBottomSheet,
                data = podcast)
        }
    }
}
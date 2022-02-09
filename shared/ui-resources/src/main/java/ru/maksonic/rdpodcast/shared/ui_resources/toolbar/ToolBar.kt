package ru.maksonic.rdpodcast.shared.ui_resources.toolbar

import androidx.fragment.app.Fragment

/**
 * @Author: maksonic on 08.02.2022
 */
interface ToolBar {
    fun initToolbar(title: String? = null, navIcon: Int? = null, fragment: Fragment?)

}
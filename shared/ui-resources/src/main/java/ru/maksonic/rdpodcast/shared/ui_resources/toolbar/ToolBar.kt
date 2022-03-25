package ru.maksonic.rdpodcast.shared.ui_resources.toolbar

import androidx.fragment.app.Fragment
import ru.maksonic.rdpodcast.shared.ui_resources.R

/**
 * @Author: maksonic on 08.02.2022
 */
interface ToolBar {
    fun initToolbar(
        toolBarTitle: String = "",
        navIcon: Int = R.drawable.ic_arrow_back,
        fragment: Fragment
    )
}
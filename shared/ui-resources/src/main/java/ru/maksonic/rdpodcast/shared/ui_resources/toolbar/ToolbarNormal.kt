package ru.maksonic.rdpodcast.shared.ui_resources.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import ru.maksonic.rdpodcast.shared.ui_resources.R
import ru.maksonic.rdpodcast.shared.ui_resources.databinding.ToolbarNormalBinding

/**
 * @Author: maksonic on 08.02.2022
 */

class ToolbarNormal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyle, defStyleRes), ToolBar {

    private var binding: ToolbarNormalBinding =
        ToolbarNormalBinding.inflate(LayoutInflater.from(context), this)

    init {}

    override fun initToolbar(toolBarTitle: String?, navIcon: Int?, fragment: Fragment?) {
        with(binding) {
            customToolBar.apply {
                title = toolBarTitle.toString()
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    fragment?.requireActivity()?.onBackPressed()
                }
            }

        }
    }
}
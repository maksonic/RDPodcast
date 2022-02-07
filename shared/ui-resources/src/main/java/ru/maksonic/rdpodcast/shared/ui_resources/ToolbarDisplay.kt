package ru.maksonic.rdpodcast.shared.ui_resources

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import ru.maksonic.rdpodcast.shared.ui_resources.databinding.ToolbarDisplayBinding

/**
 * @Author: maksonic on 06.02.2022
 */
interface ToolbarDisplayBehavior {
    fun initToolbar(title: String? = null, navIcon: Int? = null, fragment: Fragment?)
}

class ToolbarDisplay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyle, defStyleRes), ToolbarDisplayBehavior {

    private var binding: ToolbarDisplayBinding =
        ToolbarDisplayBinding.inflate(LayoutInflater.from(context), this)

    init {}

    override fun initToolbar(title: String?, navIcon: Int?, fragment: Fragment?) {
        with(binding) {
            toolBarTitle.text = title.toString()
            customToolBar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    fragment?.requireActivity()?.onBackPressed()
                }
            }

        }
    }
}
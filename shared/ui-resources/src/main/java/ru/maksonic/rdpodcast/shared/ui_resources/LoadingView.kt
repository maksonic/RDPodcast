package ru.maksonic.rdpodcast.shared.ui_resources

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.gone
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.visible
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.visibleOrGone
import ru.maksonic.rdpodcast.shared.ui_resources.databinding.ViewLoadingBinding

/**
 * @Author: maksonic on 06.02.2022
 */
class LoadingView(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet), Visibility {
    private var binding: ViewLoadingBinding =
        ViewLoadingBinding.inflate(LayoutInflater.from(context), this)

    init {
        initialViewState()
    }

    private fun initialViewState() {
        hide()
        binding.root.setBackgroundColor(context.getColor(R.color.background))
        isClickable = true
        isFocusable = true
    }

    override fun show() {
        binding.root.visible(false)
    }

    override fun hide() {
        binding.root.gone(false)
    }

    fun showOrHide(viewState: Boolean) {
        binding.root.visibleOrGone(viewState, false)
    }
}
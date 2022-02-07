package ru.maksonic.rdpodcast.shared.ui_resources

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.maksonic.rdpodcast.core.ui.click
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.gone
import ru.maksonic.rdpodcast.shared.ui_resources.UiVisibility.visible
import ru.maksonic.rdpodcast.shared.ui_resources.databinding.ViewErrorBinding

/**
 * @Author: maksonic on 06.02.2022
 */
private interface ErrorBehavior {
    fun initialViewState()
    fun acceptPressed(action: () -> Unit)
    fun setErrorMessage(message: String?)
    fun setErrorEmoji(emoji: String?)
}

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyle, defStyleRes), ErrorBehavior, Visibility {

    private var binding: ViewErrorBinding =
        ViewErrorBinding.inflate(LayoutInflater.from(context), this)

    init {
        initialViewState()
    }

    override fun initialViewState() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        hide()
        with(binding) {
            root.apply {
                setBackgroundColor(context.getColor(R.color.transparent))
                isClickable = true
                isFocusable = true
            }
        }
        setErrorEmoji(context.getString(R.string.view_state_error_emoji))
    }

    override fun show() = binding.root.visible(false)

    override fun hide() = binding.root.gone(false)

    override fun acceptPressed(action: () -> Unit) {
        with(binding) {
            btnErrorAccess.click { action.invoke() }
        }
    }

    override fun setErrorMessage(message: String?) {
        binding.errorMessage.text = message
    }

    override fun setErrorEmoji(emoji: String?) {
        binding.errorEmoji.text = emoji.toString()
    }
}
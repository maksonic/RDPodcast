package ru.maksonic.rdpodcast.feature.podcast

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdpodcast.core.base.presentation.BaseBottomSheetDialog
import ru.maksonic.rdpodcast.core.ui.DebounceClickListener
import ru.maksonic.rdpodcast.feature.podcast.databinding.BottomSheetPodcastActionBinding
import javax.inject.Inject

/**
 * @Author: maksonic on 09.02.2022
 */
@AndroidEntryPoint
class PodcastActionBottomSheet : BaseBottomSheetDialog<BottomSheetPodcastActionBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetPodcastActionBinding
        get() = BottomSheetPodcastActionBinding::inflate
    companion object {
        const val TAG = "PodcastActionBottomSheet"
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        initClicks()
    }

    private fun initClicks() {
        with(binding) {
          //  btnGoogleSignUp.setOnClickListener(clickListener)

        }
    }

    private val clickListener = object : DebounceClickListener() {
        override fun debounceClick(v: View?) {
            with(binding) {
                when (v?.id) {
                 /*   btnGoogleSignUp.id -> {
                        Toast.makeText(context, "Google auth", Toast.LENGTH_SHORT)
                            .show()
                    }*/

                }
            }
        }
    }
}

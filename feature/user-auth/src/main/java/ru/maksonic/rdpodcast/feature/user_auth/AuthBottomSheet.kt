package ru.maksonic.rdpodcast.feature.user_auth

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
import ru.maksonic.rdpodcast.feature.user_auth.databinding.BottomSheetAuthBinding
import ru.maksonic.rdpodcast.navigation.api.Navigator
import javax.inject.Inject

/**
 * @Author: maksonic on 06.02.2022
 */
@AndroidEntryPoint
class AuthBottomSheetDialog : BaseBottomSheetDialog<BottomSheetAuthBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetAuthBinding
        get() = BottomSheetAuthBinding::inflate

    @Inject
    lateinit var navigator: Navigator

    override fun prepareView(savedInstanceState: Bundle?) {
        navigator = Navigator(this)
        setClickablePrivacyAndTermsString()
        initClicks()
    }

    private fun initClicks() {
        with(binding) {
            btnGoogleSignUp.setOnClickListener(clickListener)
            btnSignUp.setOnClickListener(clickListener)
            btnLogIn.setOnClickListener(clickListener)
        }
    }

    private val clickListener = object : DebounceClickListener() {
        override fun debounceClick(v: View?) {
            with(binding) {
                when (v?.id) {
                    btnGoogleSignUp.id -> {
                        Toast.makeText(context, "Google auth", Toast.LENGTH_SHORT)
                            .show()
                    }

                    btnSignUp.id -> {
                        navigator.authToSignUpUsername()
                    }
                    btnLogIn.id -> navigator.authToLogIn()
                }
            }
        }
    }

    private fun setClickablePrivacyAndTermsString() {
        val spannableString = SpannableString(getString(R.string.privacy_and_terms_agreement))

        spannableString.apply {
            setSpan(initTermsOfUseClickableSpan(), 55, 83, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(initPrivacyClickableSpan(), 120, 147, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        binding.txtHelperTeams.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun initTermsOfUseClickableSpan(): ClickableSpan {
        val span: ClickableSpan = object : ClickableSpan() {
            val title = getString(R.string.txt_title_teams_of_use)
            val content = getString(R.string.txt_content_teams_of_use)

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.typeface = Typeface.DEFAULT_BOLD
            }

            override fun onClick(widget: View) {
                navigator.authToPrivacy(data = PrivacyPolicy(title, content))
            }
        }
        return span
    }

    private fun initPrivacyClickableSpan(): ClickableSpan {

        val span: ClickableSpan = object : ClickableSpan() {
            val title = getString(R.string.txt_title_privacy)
            val content = getString(R.string.txt_content_privacy)

            override fun onClick(widget: View) {
                navigator.authToPrivacy(data = PrivacyPolicy(title, content))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.typeface = Typeface.DEFAULT_BOLD
            }
        }
        return span
    }
}

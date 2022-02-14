package ru.maksonic.rdpodcast.feature.podcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdpodcast.core.base.presentation.BaseBottomSheetDialog
import ru.maksonic.rdpodcast.core.ui.DebounceClickListener
import ru.maksonic.rdpodcast.core.ui.toastShort
import ru.maksonic.rdpodcast.feature.podcast.databinding.BottomSheetPodcastActionBinding
import ru.maksonic.rdpodcast.navigation.api.navigationData
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi
import javax.inject.Inject

/**
 * @Author: maksonic on 09.02.2022
 */
@AndroidEntryPoint
class PodcastActionBottomSheet : BaseBottomSheetDialog<BottomSheetPodcastActionBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetPodcastActionBinding
        get() = BottomSheetPodcastActionBinding::inflate

    @Inject
    lateinit var imageLoader: RequestManager

    private var _passedData: PodcastUi? = null
    private val passedData: PodcastUi
        get() = requireNotNull(_passedData)

    override fun prepareView(savedInstanceState: Bundle?) {
        _passedData = navigationData as? PodcastUi
        initClicks()
        binding.txtPodcastName.apply {
            text = passedData.name
            isSelected = true
        }
        imageLoader.load(passedData.image).into(binding.imgPodcast)
    }

    private fun initClicks() {
        with(binding) {
            btnPlayPodcast.setOnClickListener(clickListener)
            btnFavoritePodcast.setOnClickListener(clickListener)
            btnDownloadPodcast.setOnClickListener(clickListener)
            btnYoutubePodcast.setOnClickListener(clickListener)
            btnSharePodcast.setOnClickListener(clickListener)

        }
    }

    private val clickListener = object : DebounceClickListener() {
        override fun debounceClick(v: View?) {
            with(binding) {
                when (v?.id) {
                    btnPlayPodcast.id -> toastShort(context, "Play")
                    btnFavoritePodcast.id -> toastShort(context, "Add to favorite")
                    btnDownloadPodcast.id -> toastShort(context, "Download")
                    btnYoutubePodcast.id -> toastShort(context, "Go to Youtube")
                    btnSharePodcast.id -> toastShort(context, "Share")

                }
            }
        }
    }
}

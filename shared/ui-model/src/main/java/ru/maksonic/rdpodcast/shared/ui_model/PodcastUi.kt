package ru.maksonic.rdpodcast.shared.ui_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author: maksonic on 09.02.2022
 */
@Parcelize
data class PodcastUi(
    val id: Long? = null,
    val categoryId: String = "",
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
    val isCurrentPlaying: Boolean = false,
    val isFavorite: Boolean = false,
    val isDownloaded: Boolean = false
) : Parcelable
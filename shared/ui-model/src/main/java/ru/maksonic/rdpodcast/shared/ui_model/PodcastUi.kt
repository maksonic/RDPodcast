package ru.maksonic.rdpodcast.shared.ui_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author: maksonic on 09.02.2022
 */
@Parcelize
data class PodcastUi(
    val id: Long,
    val categoryId: String? = null,
    val name: String? = null,
    val image: String? = null,
    val soundFile: String? = null,
    ) : Parcelable
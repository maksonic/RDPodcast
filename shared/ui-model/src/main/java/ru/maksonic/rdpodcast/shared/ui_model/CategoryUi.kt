package ru.maksonic.rdpodcast.shared.ui_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author: maksonic on 07.02.2022
 */
@Parcelize
data class CategoryUi(
    val id: Long,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
) : Parcelable
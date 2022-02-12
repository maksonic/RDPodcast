package ru.maksonic.rdpodcast.data.podcast

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author: maksonic on 10.02.2022
 */
data class PodcastData(
    @SerializedName("categoryId") val categoryId: String? = null,
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("soundfile") val soundFile: String? = null,
) : Serializable
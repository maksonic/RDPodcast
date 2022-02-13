package ru.maksonic.rdpodcast.data.podcast

import com.google.gson.annotations.SerializedName
import ru.maksonic.rdpodcast.core.base.Abstract
import java.io.Serializable

/**
 * @Author: maksonic on 10.02.2022
 */
data class PodcastData(
    @SerializedName("categoryId") val categoryId: String = "",
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("image") val image: String = "",
    @SerializedName("soundfile") val soundFile: String = "",
) : Serializable, Abstract.DataObject
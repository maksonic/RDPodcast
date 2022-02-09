package ru.maksonic.rdpodcast.data.categories

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author: maksonic on 07.02.2022
 */
data class CategoryData(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image") val image: String? = null,
) : Serializable
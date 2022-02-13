package ru.maksonic.rdpodcast.data.categories

import com.google.gson.annotations.SerializedName
import ru.maksonic.rdpodcast.core.base.Abstract
import java.io.Serializable

/**
 * @Author: maksonic on 07.02.2022
 */
data class CategoryData(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("categoryId") val categoryId: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("image") val image: String = "",
) : Serializable, Abstract.DataObject
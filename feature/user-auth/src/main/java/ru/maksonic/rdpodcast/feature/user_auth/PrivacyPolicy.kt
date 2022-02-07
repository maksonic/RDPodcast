package ru.maksonic.rdpodcast.feature.user_auth

import android.os.Parcel
import android.os.Parcelable

/**
 * @Author: maksonic on 06.02.2022
 */
data class PrivacyPolicy(
    val title: String,
    val content: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PrivacyPolicy> {
        override fun createFromParcel(parcel: Parcel): PrivacyPolicy {
            return PrivacyPolicy(parcel)
        }

        override fun newArray(size: Int): Array<PrivacyPolicy?> {
            return arrayOfNulls(size)
        }
    }
}

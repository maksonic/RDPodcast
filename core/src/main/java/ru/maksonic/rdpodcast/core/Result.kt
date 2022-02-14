package ru.maksonic.rdpodcast.core

/**
 * @Author: maksonic on 07.02.2022
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
}

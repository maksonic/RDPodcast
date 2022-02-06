package ru.maksonic.rdpodcast.core.base.presentation.architecture

/**
 * @Author: maksonic on 06.02.2022
 */
interface CommandHandler<out Cmd, Msg> {
    suspend fun execute(cmd: @UnsafeVariance Cmd, consumer: (Msg) -> Unit)
}

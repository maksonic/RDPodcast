package ru.maksonic.rdpodcast.core.base.presentation.architecture

import kotlinx.coroutines.flow.Flow

/**
 * @Author: maksonic on 06.02.2022
 */
interface Update<Msg, State, Cmd> {
    val state: Flow<State>
    fun dispatch(msg: Msg)
    fun update(msg: Msg, state: State): Pair<State, Set<Cmd>>
}
package ru.maksonic.rdpodcast.core.base.presentation.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * @Author: maksonic on 06.02.2022
 */
abstract class FeatureHolder<Msg : Any, State : Any, Cmd : Any>(
    initState: State,
    private val initCommands: Set<Cmd>,
    vararg handlers: Pair<KClass<out Cmd>, CommandHandler<Cmd, Msg>>,
) : ViewModel(), Update<Msg, State, Cmd> {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initState)
    override val state: Flow<State> = _state.filterNotNull()
    private val commandHandlers = handlers.toList()

    constructor(
        initState: State,
        vararg handlers: Pair<KClass<out Cmd>, CommandHandler<Cmd, Msg>>,
    ) : this(initState, initCommands = emptySet(), *handlers)

    init {
        viewModelScope.launch {
            executeCommands(initCommands)
        }
    }

    override fun dispatch(msg: Msg) {
        val (newState, commands) = update(msg, _state.value)
        _state.value = newState
        executeCommands(commands)
    }

    private fun executeCommands(commands: Set<Cmd>) {
        commands.forEach { cmd ->
            viewModelScope.launch {
                commandHandlers.forEach { (commandClass, handler) ->
                    if (commandClass.isInstance(cmd)) {
                        handler.execute(cmd, ::dispatch)
                    }
                }
            }
        }
    }
}
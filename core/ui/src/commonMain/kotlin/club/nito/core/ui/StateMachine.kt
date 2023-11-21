package club.nito.core.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * [StateMachine]
 */
public open class StateMachine {
    public val stateMachineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}

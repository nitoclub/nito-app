package club.nito.core.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moe.tlaster.precompose.viewmodel.ViewModel

/**
 * [StateMachine]
 */
public open class StateMachine: ViewModel() {
    public val stateMachineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}

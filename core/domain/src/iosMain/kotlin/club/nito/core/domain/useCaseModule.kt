package club.nito.core.domain

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule: Module = module {
    singleOf(::ObserveAuthStatusExecutor) bind ObserveAuthStatusUseCase::class
    singleOf(::SignInExecutor) bind SignInUseCase::class
    singleOf(::SignOutExecutor) bind SignOutUseCase::class
    singleOf(::GetRecentScheduleExecutor) bind GetRecentScheduleUseCase::class
}

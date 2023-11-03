package club.nito.core.data

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule: Module = module {
    singleOf(::DefaultAuthRepository) bind AuthRepository::class
    singleOf(::OfflineFirstScheduleRepository) bind ScheduleRepository::class
    singleOf(::DefaultParticipationRepository) bind ParticipationRepository::class
}

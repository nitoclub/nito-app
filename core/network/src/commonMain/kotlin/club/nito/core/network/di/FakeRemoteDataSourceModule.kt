package club.nito.core.network.di

import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.auth.FakeAuthRemoteDataSource
import club.nito.core.network.participation.FakeParticipantRemoteDataSource
import club.nito.core.network.participation.ParticipantRemoteDataSource
import club.nito.core.network.schedule.FakeScheduleRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import club.nito.core.network.user.FakeUserRemoteDataSource
import club.nito.core.network.user.UserRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.dsl.module

public val fakeRemoteDataSourceModule: Module = module {
    single<AuthRemoteDataSource> {
        FakeAuthRemoteDataSource(
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        )
    }
    single<ScheduleRemoteDataSource> { FakeScheduleRemoteDataSource }
    single<ParticipantRemoteDataSource> { FakeParticipantRemoteDataSource }
    single<UserRemoteDataSource> { FakeUserRemoteDataSource }
}

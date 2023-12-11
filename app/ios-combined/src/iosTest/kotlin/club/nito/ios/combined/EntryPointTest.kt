package club.nito.ios.combined

import club.nito.core.data.AuthRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.domain.AuthStatusStreamUseCase
import club.nito.core.domain.MyParticipantStatusStreamUseCase
import club.nito.core.domain.FetchParticipantScheduleByIdUseCase
import club.nito.core.domain.GetParticipantScheduleListUseCase
import club.nito.core.domain.GetRecentScheduleUseCase
import club.nito.core.domain.LoginUseCase
import club.nito.core.domain.LogoutUseCase
import club.nito.core.domain.ModifyPasswordUseCase
import club.nito.core.domain.ParticipateUseCase
import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.participation.ParticipantRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.Auth
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNotNull

class EntryPointTest {
    @Test
    fun get() {
        val kmpEntryPoint = KmpEntryPoint()
        kmpEntryPoint.init()
        // Check finer dependencies first to debug easily
        assertNotNull(kmpEntryPoint.get<SupabaseClient>())
        assertNotNull(kmpEntryPoint.get<Json>())
        assertNotNull(kmpEntryPoint.get<Auth>())

        assertNotNull(kmpEntryPoint.get<AuthRemoteDataSource>())
        assertNotNull(kmpEntryPoint.get<ScheduleRemoteDataSource>())
        assertNotNull(kmpEntryPoint.get<ParticipantRemoteDataSource>())

        assertNotNull(kmpEntryPoint.get<AuthRepository>())
        assertNotNull(kmpEntryPoint.get<ScheduleRepository>())

        assertNotNull(kmpEntryPoint.get<AuthStatusStreamUseCase>())
        assertNotNull(kmpEntryPoint.get<LoginUseCase>())
        assertNotNull(kmpEntryPoint.get<ModifyPasswordUseCase>())
        assertNotNull(kmpEntryPoint.get<LogoutUseCase>())
        assertNotNull(kmpEntryPoint.get<FetchParticipantScheduleByIdUseCase>())
        assertNotNull(kmpEntryPoint.get<GetRecentScheduleUseCase>())
        assertNotNull(kmpEntryPoint.get<GetParticipantScheduleListUseCase>())
        assertNotNull(kmpEntryPoint.get<ParticipateUseCase>())
        assertNotNull(kmpEntryPoint.get<MyParticipantStatusStreamUseCase>())
    }
}

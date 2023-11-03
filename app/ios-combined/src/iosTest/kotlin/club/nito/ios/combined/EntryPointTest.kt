package club.nito.ios.combined

import club.nito.core.data.AuthRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.domain.GetRecentScheduleUseCase
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.domain.SignInUseCase
import club.nito.core.domain.SignOutUseCase
import club.nito.core.network.auth.AuthRemoteDataSource
import club.nito.core.network.participation.ParticipationRemoteDataSource
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
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
        assertNotNull(kmpEntryPoint.get<GoTrue>())

        assertNotNull(kmpEntryPoint.get<AuthRemoteDataSource>())
        assertNotNull(kmpEntryPoint.get<ScheduleRemoteDataSource>())
        assertNotNull(kmpEntryPoint.get<ParticipationRemoteDataSource>())

        assertNotNull(kmpEntryPoint.get<AuthRepository>())
        assertNotNull(kmpEntryPoint.get<ScheduleRepository>())

        assertNotNull(kmpEntryPoint.get<ObserveAuthStatusUseCase>())
        assertNotNull(kmpEntryPoint.get<SignInUseCase>())
        assertNotNull(kmpEntryPoint.get<SignOutUseCase>())
        assertNotNull(kmpEntryPoint.get<GetRecentScheduleUseCase>())
    }
}
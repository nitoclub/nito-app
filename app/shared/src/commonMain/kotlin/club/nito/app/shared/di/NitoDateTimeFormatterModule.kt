package club.nito.app.shared.di

import club.nito.core.common.NitoDateTimeFormatter
import org.koin.dsl.module

val nitoDateTimeFormatterModule = module {
    single<NitoDateTimeFormatter> {
        createNitoDateTimeFormatter()
    }
}

expect fun createNitoDateTimeFormatter(): NitoDateTimeFormatter

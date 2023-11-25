package club.nito.app.shared.di

import club.nito.core.common.NitoDateFormatter
import org.koin.dsl.module

val nitoDateFormatterModule = module {
    single<NitoDateFormatter> {
        createNitoDateTimeFormatter()
    }
}

expect fun createNitoDateTimeFormatter(): NitoDateFormatter

package club.nito.app.di

import club.nito.core.common.DefaultNitoDateTimeFormatter
import club.nito.core.common.NitoDateTimeFormatter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class NitoDateTimeFormatterModule {
    @Provides
    fun provideNitoDateTimeFormatter(): NitoDateTimeFormatter = DefaultNitoDateTimeFormatter(
        dateTimeFormatter = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault()),
    )
}

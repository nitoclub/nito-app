package club.nito.app.shared.di

import club.nito.core.common.DefaultNitoDateTimeFormatter
import club.nito.core.common.NitoDateTimeFormatter
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

actual fun createNitoDateTimeFormatter(): NitoDateTimeFormatter = DefaultNitoDateTimeFormatter(
    dateTimeFormatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault()),
)

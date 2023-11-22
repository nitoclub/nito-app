package club.nito.app.di

import club.nito.feature.schedule.di.scheduleFeatureModule
import club.nito.feature.top.di.topFeatureModule
import org.koin.core.module.Module

val featureModules: List<Module> = listOf(
    topFeatureModule,
    scheduleFeatureModule,
)

plugins {
    id("nito.convention.androidfeature")
}

android.namespace = "club.nito.feature.top"

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.model)
}

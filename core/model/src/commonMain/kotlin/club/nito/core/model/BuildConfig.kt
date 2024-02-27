package club.nito.core.model

/**
 * Provides information about the build configuration of the application.
 */
public interface BuildConfig {
    /**
     * Unique ID of the application.
     */
    public val applicationId: String

    /**
     * The version name of the application.
     */
    public val versionName: String

    /**
     * True if the application is built in debug mode.
     */
    public val debugBuild: Boolean

    public companion object {
        public val Empty: BuildConfig = EmptyBuildConfig
    }
}

/**
 * Empty implementation of [BuildConfig].
 */
private object EmptyBuildConfig : BuildConfig {
    override val applicationId: String = ""
    override val versionName: String = ""
    override val debugBuild: Boolean = false
}

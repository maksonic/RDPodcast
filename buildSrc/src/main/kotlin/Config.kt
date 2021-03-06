import org.gradle.api.JavaVersion

/**
 * @Author: maksonic on 05.02.2022
 */
object Config {
    const val applicationId = "ru.maksonic.rdpodcast"
    const val kotlinVersion = "1.5.31"
    const val buildToolsVersion = "30.0.1"
    const val compileSdk = 32
    const val targetSdk = 31
    const val minSdk = 23
    const val versionName = "1.0"
    const val versionCode = 1
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val androidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"

    val javaVersion = JavaVersion.VERSION_11
    const val jvmTarget = "11"
}

object BuildType {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}
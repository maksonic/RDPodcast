/**
 * @Author: maksonic on 05.02.2022
 */
object BuildPlugin {

    object GoogleServices {
        private const val version = "4.3.3"
        const val init = "com.google.gms:google-services:$version"
    }

    object HiltGradle {
        private const val version = "2.40"
        const val init = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object KotlinGradle {
        private const val version = "1.6.10"
        const val init = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object ToolsGradle {
        private const val version = "7.0.4"
        const val init = "com.android.tools.build:gradle:$version"
    }

}
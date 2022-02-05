/**
 * @Author: maksonic on 05.02.2022
 */
class Lib {

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val material = "com.google.android.material:material:1.5.0"
    }

    object Navigation {
        private const val version = "2.4.0"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"

    }


    object TestLibraries {
        const val junit = "junit:junit:4.13"
    }

    object AndroidTestLibraries {
        const val junitExt = "androidx.test.ext:junit:1.1.3"

        object Espresso {
            private const val version = "3.4.0"
            const val core = "androidx.test.espresso:espresso-core:$version"
        }
    }
}
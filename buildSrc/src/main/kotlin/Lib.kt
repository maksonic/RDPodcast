/**
 * @Author: maksonic on 05.02.2022
 */
object Lib {

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val material = "com.google.android.material:material:1.5.0"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

    }

    const val glide = "com.github.bumptech.glide:glide:4.13.0"

    object Dagger {
        private const val version = "2.40.5"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
    }

    const val gson = "com.google.code.gson:gson:2.8.9"

    object Emoji {
        private const val version = "1.0.0"

        const val emoji2 = "androidx.emoji2:emoji2:$version"
        const val bundled = "androidx.emoji2:emoji2-bundled:$version"
        const val views = "androidx.emoji2:emoji2-views:$version"
        const val emoji2ViewsHelper = "androidx.emoji2:emoji2-views-helper:$version"
    }

    object Firebase {

        const val authKtx = "com.google.firebase:firebase-auth-ktx:21.0.1"
        const val firestore = "com.google.firebase:firebase-firestore-ktx:24.0.1"
        const val googlePlay = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.1"
    }


    object Jetbrains {
        private const val version = "1.6.0"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val coroutinesPlayService =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
    }

    object Lifecycle {
        private const val version = "2.4.0"

        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$version" // viewModelScope
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version" // lifecycleScope
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
    }

    object Navigation {
        private const val version = "2.4.1"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"

    }

    const val okHttp = "com.squareup.okhttp3:okhttp:4.9.3"


    object RoomDatabase {
        private const val roomVersion = "2.3.0"
        const val annotationProcessor = "androidx.room:room-compiler:$roomVersion"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }


    const val timber = "com.jakewharton.timber:timber:5.0.1"

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
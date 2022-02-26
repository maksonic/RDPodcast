plugins {
    androidLibrary()
    kotlinAndroid()
    hilt()
    kapt()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        testInstrumentationRunner = Config.androidJunitRunner
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Lib.AndroidX.material)
    implementation(Lib.Firebase.firestore)
    implementation(Lib.Navigation.fragment)
    implementation(Lib.RoomDatabase.roomKtx)
    implementation(Lib.Dagger.hilt)
    implementation(Lib.glide)
    implementation ("com.google.android.exoplayer:exoplayer:2.16.1")
    implementation(Lib.timber)
    kapt(Lib.Dagger.hiltCompiler)

}
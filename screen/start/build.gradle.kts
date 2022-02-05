plugins {
    androidLibrary()
    kotlinAndroid()
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
}

dependencies {
    implementation(Lib.AndroidX.coreKtx)
    implementation(Lib.AndroidX.appCompat)
    implementation(Lib.AndroidX.material)
    implementation(Lib.Navigation.fragment)
    implementation(Lib.Navigation.ui)

    testImplementation(Lib.TestLibraries.junit)
    androidTestImplementation(Lib.AndroidTestLibraries.junitExt)
    androidTestImplementation(Lib.AndroidTestLibraries.Espresso.core)
}
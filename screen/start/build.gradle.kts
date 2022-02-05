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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Module.CORE))
    implementation(project(Navigation.IMPL))

    implementation(Lib.AndroidX.coreKtx)
    implementation(Lib.AndroidX.appCompat)
    implementation(Lib.AndroidX.material)

    testImplementation(Lib.TestLibraries.junit)
    androidTestImplementation(Lib.AndroidTestLibraries.junitExt)
    androidTestImplementation(Lib.AndroidTestLibraries.Espresso.core)
}
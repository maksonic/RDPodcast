plugins {
    androidApp()
    kotlinAndroid()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.androidJunitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(project(Module.CORE))
    implementation(project(Screen.ONBOARDING))
    implementation(project(Screen.MAIN))
    implementation(Lib.AndroidX.coreKtx)
    implementation(Lib.AndroidX.appCompat)
    implementation(Lib.AndroidX.material)

    testImplementation(Lib.TestLibraries.junit)
    androidTestImplementation(Lib.AndroidTestLibraries.junitExt)
    androidTestImplementation(Lib.AndroidTestLibraries.Espresso.core)
}
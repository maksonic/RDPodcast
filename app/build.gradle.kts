plugins {
    androidApp()
    kotlinAndroid()
    googleServices()
    hilt()
    kapt()
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Module.CORE))
    implementation(project(Module.DATA))
    implementation(project(Module.DOMAIN))
    implementation(project(Shared.UI_MODEL))
    implementation(project(Shared.UI_RES))
    implementation(project(Navigation.API))
    implementation(project(Navigation.IMPL))
    implementation(project(Screen.ONBOARDING))
    implementation(project(Screen.MAIN))
    implementation(project(Screen.START))
    implementation(project(Screen.CATEGORIES))
    implementation(project(Screen.COLLECTIONS))

    implementation(Lib.Emoji.emoji2)
    implementation(Lib.Emoji.bundled)
    implementation(Lib.timber)
    implementation(Lib.Dagger.hilt)
    kapt(Lib.Dagger.hiltCompiler)

    testImplementation(Lib.TestLibraries.junit)
    androidTestImplementation(Lib.AndroidTestLibraries.junitExt)
    androidTestImplementation(Lib.AndroidTestLibraries.Espresso.core)
}
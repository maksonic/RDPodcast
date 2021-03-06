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
}

dependencies {
    implementation(project(Shared.UI_MODEL))
    implementation(project(Navigation.API))
    implementation(project(Screen.ONBOARDING))
    implementation(project(Screen.HOME))
    implementation(project(Screen.CATEGORIES))
    implementation(project(Screen.COLLECTIONS))
    implementation(project(Screen.PODCAST_LIST))
    implementation(project(Screen.SETTINGS))
    implementation(project(Feature.USER_AUTH))
    implementation(project(Feature.USER_PROFILE))
    implementation(project(Feature.PODCAST))
    implementation(Lib.Navigation.fragment)
    implementation(Lib.Navigation.ui)

    implementation(Lib.Dagger.hilt)
    kapt(Lib.Dagger.hiltCompiler)
}
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
    implementation(project(Module.CORE))
    implementation(project(Module.DOMAIN))
    implementation(project(Feature.PODCAST))
    implementation(project(Navigation.API))
    implementation(Lib.Navigation.fragment)
    implementation(project(Shared.UI_RES))
    implementation(project(Shared.UI_MODEL))
    implementation(Lib.AndroidX.appCompat)
    implementation(Lib.AndroidX.material)
    implementation(Lib.Lifecycle.runtimeKtx)
    implementation(Lib.AndroidX.swipeRefreshLayout)
    implementation(Lib.Dagger.hilt)
    kapt(Lib.Dagger.hiltCompiler)
}
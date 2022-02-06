buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugin.GoogleServices.init)
        classpath(BuildPlugin.HiltGradle.init)
        classpath(BuildPlugin.KotlinGradle.init)
        classpath(BuildPlugin.ToolsGradle.init)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
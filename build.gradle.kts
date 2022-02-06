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
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
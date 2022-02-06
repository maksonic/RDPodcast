dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "RDPodcast"
include (":app")
include(":core")
include(":screen")
include(":screen:main")
include(":screen:onboarding")
include(":screen:start")
include(":navigation")
include(":navigation:impl")
include(":navigation:api")
include(":shared")
include(":shared:ui-model")
include(":shared:ui-resources")
include(":data")
include(":domain")
include(":screen:categories")
include(":screen:collections")
include(":feature:user-auth")

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
include(":screen:onboarding")

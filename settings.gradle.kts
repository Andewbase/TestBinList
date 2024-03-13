pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BinList-Compose"
include(":app")
include(":binlistapi")
include(":database")
include(":binlistdata")
include(":core")
include(":features:binlist-main")
include(":binlist-uikit")


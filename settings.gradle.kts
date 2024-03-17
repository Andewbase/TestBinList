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
include(":core")
include(":features:binlist-main")
include(":binlist-uikit")
include(":binlist-api")
include(":binlist-database")
include(":binlist-data")
include(":binlist-navigation")

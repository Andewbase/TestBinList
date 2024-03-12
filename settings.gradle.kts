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

rootProject.name = "BinList-Compouse"
include(":app")
include(":binlistapi")
include(":database")
include(":binlist-main")
include(":binlistdata")
include(":core")
include(":features:binlist-home")
include(":binlist-uikit")

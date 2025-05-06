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
        maven { url = uri("https://www.jitpack.io") }
    }
}

// ✅ This must be outside of the above blocks:
rootProject.name = "Auto_Slider"
include(":app")  // ✅ Correct function syntax with parentheses and quotes

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20-Beta1" apply false
    id("com.google.devtools.ksp") version "2.1.20-Beta1-1.0.29" apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "2.1.20-Beta1" apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.8.5"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

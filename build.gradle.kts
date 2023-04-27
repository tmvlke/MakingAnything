// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.0-1.0.8")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")

        // test
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

plugins {
    id("com.android.application") version("8.0.0") apply false
    id("com.android.library") version("8.0.0") apply false
    id("org.jetbrains.kotlin.android") version("1.8.0") apply false

    id("org.jetbrains.kotlin.jvm") version("1.8.0") apply false
    id("com.google.devtools.ksp") version("1.8.0-1.0.8")
}
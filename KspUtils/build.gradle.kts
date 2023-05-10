plugins {
    kotlin("jvm")
    id("kotlin-kapt")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.21-1.0.11")
}
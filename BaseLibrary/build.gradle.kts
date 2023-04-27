plugins {
    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
}

android {
    namespace = "wskim.baselibrary"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
//        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
//        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        // AGP 9.0 이전까지만 사용하기
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//    kotlin {
//        jvmToolchain(17)
//    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")

    // okhttp
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // retrofit
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:adapter-rxjava:2.9.0")
    api("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // glide
    api("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
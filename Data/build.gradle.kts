plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "wskim.corefeature"
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
        buildConfig = false
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }
    buildToolsVersion = "34.0.0 rc3"
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//    kotlin {
//        jvmToolchain(17)
//    }
}

dependencies {
    implementation(project(":Domain"))

    // okhttp
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // SharedPreferences
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("com.localebro:okhttpprofiler:1.0.8")

    implementation("com.google.dagger:hilt-android:2.46.1")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

    val room_version = "2.5.0"

    api("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
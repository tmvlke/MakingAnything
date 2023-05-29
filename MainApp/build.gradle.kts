plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

dependencies {
    implementation(project(":BaseLibrary"))
    implementation(project(":CoreFeature"))


    implementation("androidx.core:core-ktx:1.10.1")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")


    implementation("androidx.activity:activity-ktx:1.7.2")

    implementation("com.google.dagger:hilt-android:2.46.1")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

    implementation("org.greenrobot:eventbus:3.3.1")

//    implementation("com.google.accompanist:accompanist-coil:0.16.0")

    // compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.05.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.navigation:navigation-compose:2.6.0-rc01")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.compose.compiler:compiler:1.4.7")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.2-alpha")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10")

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha19")

    implementation("com.airbnb.android:lottie-compose:6.0.0")





    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1") {
        // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-debug#debug-agent-and-android
        exclude(
            group = "org.jetbrains.kotlinx",
            module = "kotlinx-coroutines-debug"
        )
    }
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("io.mockk:mockk:1.13.5")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.9.3")

    // android test
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.3.0")

    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.3.0")
}


android {
    namespace = "wskim.main_app"
    compileSdk = 33
    buildToolsVersion = "34.0.0 rc3"
    ndkVersion = "25.1.8937393"

    defaultConfig {
//        applicationId("wskim")
//        minSdkVersion(28)
//        targetSdkVersion(33)
//        versionCode(1)
//        versionName("1.0")

        applicationId = "wskim.main_app"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"


        buildConfigField("String", "SERVER_URL", "\"https://dapi.kakao.com\"")

//        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
//        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    val isDebug = android.buildTypes.getByName("debug").isDebuggable
    val buildType = if (isDebug) {
        "Debug"
    } else {
        "Release"
    }

    buildFeatures {
        // AGP 9.0 이전까지만 사용하기
        buildConfig = true

        // compose 활성화 시 주석 풀기
        compose = true

    }

    flavorDimensions.add("version")
    productFlavors {
        create("dev") {
            dimension = "version"

            sourceSets {
                getByName("main") {
                }
            }
        }
        create("prod") {
            dimension = "version"

            sourceSets {
                getByName("main") {
                }
            }
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.1"
    }
//    kotlin {
//        jvmToolchain(11)
//    }
}
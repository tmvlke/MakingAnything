plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

dependencies {
    implementation(project(":BaseLibrary"))
    implementation(project(":CoreFeature"))

//    implementation(project(":KspUtils"))
//    ksp(project(":KspUtils"))


    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.7.0-alpha02")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("androidx.core:core-splashscreen:1.0.1")

//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
//    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.1")
//    implementation("androidx.lifecycle:lifecycle-view-model-ktx:2.6.0")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    implementation("androidx.activity:activity-ktx:1.7.1")
    implementation("androidx.fragment:fragment-ktx:1.6.0-beta01")

    implementation("com.google.dagger:hilt-android:2.45")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    kapt("com.google.dagger:hilt-android-compiler:2.45")

    implementation("org.greenrobot:eventbus:3.3.1")

//    implementation("com.google.accompanist:accompanist-coil:0.16.0")

    // compose
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha09")


    // view pager
    // https://google.github.io/accompanist/pager/
//    implementation("com.google.accompanist:accompanist-pager:0.24.13-rc")
//    implementation("com.google.accompanist:accompanist-pager-indicators:0.24.13-rc")




//    androidTestImplementation("latform('androidx.compose:compose-bom:2022.10.00'")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4") {
        // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-debug#debug-agent-and-android
        exclude(
            group = "org.jetbrains.kotlinx",
            module = "kotlinx-coroutines-debug"
        )
    }
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("io.mockk:mockk:1.13.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.2")

    // android test
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.3.0")

    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.3.0")
}


android {
    namespace = "wskim.main_app"
    compileSdk = 33
    buildToolsVersion = "33.0.0 rc2"
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

        viewBinding = true
        dataBinding = true

        // compose 활성화 시 주석 풀기
        compose = true

    }

    flavorDimensions.add("version")
    productFlavors {
        create("dev") {
            dimension = "version"

            sourceSets {
                getByName("main") {
//                    kotlin.srcDir("build/generated/ksp/${flavorType}Release/kotlin")
                    java.srcDir(File("build/generated/ksp/dev${buildType}/kotlin"))
                }
            }
        }
        create("prod") {
            dimension = "version"

            sourceSets {
                getByName("main") {
//                    kotlin.srcDir("build/generated/ksp/${flavorType}Release/kotlin")
                    java.srcDir(File("build/generated/ksp/prod${buildType}/kotlin"))
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

ksp {
    arg("projectName", "wskim.main_app")
}
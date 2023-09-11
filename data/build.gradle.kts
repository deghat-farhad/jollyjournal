plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "1.8.10"
    kotlin("kapt")
}

android {
    namespace = "com.farhad.jollyjournal.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    implementation(Network.retrofit)
    implementation(Network.okhttp)
    implementation(Network.retrofitSerialization)
    implementation(Dependencies.kotlinSerializationJson)

    implementation(Room.room)
    ksp(Room.roomCompiler)

    testImplementation (Test.mockito)
    testImplementation (Test.coroutineTest)
    testImplementation(Test.junit)
}
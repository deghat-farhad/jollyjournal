plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.8.10"
    kotlin("kapt")
}

android {
    namespace = "com.farhad.jollyjournal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.farhad.jollyjournal"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)
    implementation(Hilt.hiltNavigation)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.kotlinSerializationJson)
    implementation(Dependencies.materialIcon)
    implementation(Dependencies.coilCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.material3)

    testImplementation (Test.mockito)
    testImplementation (Test.coroutineTest)
    testImplementation(Test.turbine)
    testImplementation(Test.junit)
}
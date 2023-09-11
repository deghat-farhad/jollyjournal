object Version{
    const val hilt = "2.44"
    const val hiltNavigation = "1.0.0"
    const val composeNavigation = "2.7.2"
    const val kotlinSerialization = "1.5.1"
    const val materialIcon = "1.5.1"
    const val coilCompose = "2.4.0"
    const val mockito = "5.5.0"
    const val coroutineTest = "1.7.3"
    const val tourbine = "1.0.0"
    const val jUnit = "4.13.2"
    const val composeBom = "2023.03.00"
    const val room = "2.5.2"
    const val retrofit = "2.9.0"
    const val okhttp = "4.11.0"
    const val retrofitSerialization = "1.0.0"
}

object Dependencies {
    const val composeNavigation = "androidx.navigation:navigation-compose:${Version.composeNavigation}"
    const val kotlinSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinSerialization}"
    const val materialIcon = "androidx.compose.material:material-icons-extended:${Version.materialIcon}"
    const val coilCompose = "io.coil-kt:coil-compose:${Version.coilCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Version.composeBom}"
    const val material3 = "androidx.compose.material3:material3"
}

object Hilt{
    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler =  "com.google.dagger:hilt-compiler:${Version.hilt}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigation}"
}

object Test{
    const val mockito = "org.mockito:mockito-core:${Version.mockito}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutineTest}"
    const val turbine = "app.cash.turbine:turbine:${Version.tourbine}"
    const val junit = "junit:junit:${Version.jUnit}"
}

object Room{
    const val room = "androidx.room:room-ktx:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
}

object Network{
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
    const val retrofitSerialization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Version.retrofitSerialization}"
}
//noinspection DuplicatePlatformClasses
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val compose_version = "1.3.3"


android {
    namespace = "my.dreamtech.trackstockapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "my.dreamtech.trackstockapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation("androidx.compose.material3:material3-android:1.2.1")
    val compose_version = "1.3.3"
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // OpenCSV
    implementation("com.opencsv:opencsv:5.5.2")

    // Compose dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("androidx.activity:activity-compose:1.6.0-alpha01")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")

    // Compose Nav Destinations
    implementation("io.github.raamcosta.compose-destinations:core:1.1.2-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.1.2-beta")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")


    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("androidx.navigation:navigation-compose:2.5.3")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    implementation ("androidx.collection:collection-jvm:1.3.0")


    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    //Gemini AI
    implementation("com.google.ai.client.generativeai:generativeai:0.2.2")

    implementation("io.coil-kt:coil-compose:2.5.0")

}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.enons.vehicleapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.enons.vehicleapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 2
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
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.camera:camera-core:1.3.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.libraries.mapsplatform.transportation:transportation-consumer:2.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //ViewModel
    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("com.google.code.gson:gson:2.8.6")
    //Live data
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    //Room
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    //Coroutine
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    //Sqlite
    implementation("androidx.sqlite:sqlite:2.2.6")
    //Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // Lifecycle component & Kotlin coroutines components
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    // Google Play Review
    implementation ("com.google.android.play:review:2.0.1")
    implementation ("com.google.android.play:review-ktx:2.0.1")
    implementation("com.google.android.gms:play-services-ads:22.6.0")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.navigation:navigation-compose:2.7.5")

}

kapt {
    correctErrorTypes = true
}
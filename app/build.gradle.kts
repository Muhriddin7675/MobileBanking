plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.mobilebanking"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mobilebanking"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Better Logging In Android Using Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")


    val voyagerVersion = "1.0.0"
    // Multiplatform

    // Navigator
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    // Screen Model
    implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")

    // BottomSheetNavigator
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")

    // TabNavigator
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

    // Transitions
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    // Hilt integration
    implementation("cafe.adriel.voyager:voyager-hilt:$voyagerVersion")



    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation("androidx.compose.material:material:1.6.6")

    implementation ("com.google.accompanist:accompanist-coil:0.11.1")

    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation ("io.github.grizzi91:bouquet:1.1.2")

    //orbit
    // MVI orbit
    implementation("org.orbit-mvi:orbit-viewmodel:4.6.1")
    implementation("org.orbit-mvi:orbit-compose:4.6.1")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    // lottie
    implementation("com.airbnb.android:lottie-compose:4.0.0")


    /**
     * REST API: Adding retrofit to the mainLayer
     */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    /**
     *  Chuck
     */
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    /**
     * Gson
     * */
    implementation("com.google.code.gson:gson:2.10.1")

}
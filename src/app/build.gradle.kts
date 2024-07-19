plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "G26.Project"
    compileSdk = 33

    defaultConfig {
        applicationId = "G26.Project"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            getByName("debug") {
                isTestCoverageEnabled = true
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

    testOptions.unitTests.isReturnDefaultValues = true
    testOptions.unitTests.isIncludeAndroidResources = true
    testOptions.unitTests.isReturnDefaultValues = true

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.firebase:firebase-firestore:24.8.1")
    implementation ("com.google.firebase:firebase-storage:latest_version")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.android.gms:play-services-location:18.0.0")
    implementation("com.google.firebase:firebase-auth:22.1.2")
    implementation ("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation("androidx.annotation:annotation:1.7.0")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test:runner:1.4.0")
    androidTestImplementation ("androidx.test:rules:1.4.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.github.bumptech.glide:glide:latest_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("junit:junit:4.13.2")

    testImplementation("org.mockito:mockito-core:3.11.2")
    testImplementation("org.powermock:powermock-module-junit4:2.0.9")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.9")

    testImplementation ("org.robolectric:robolectric:4.10.3")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.2") // Adjusted version
    androidTestImplementation("androidx.test:runner:1.3.0") // Adjusted version

}
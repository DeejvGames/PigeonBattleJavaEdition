plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "pl.deejvgames.pigeonbattlejavaedition"
    compileSdk = 35

    defaultConfig {
        applicationId = "pl.deejvgames.pigeonbattlejavaedition"
        minSdk = 28
        targetSdk = 35
        versionCode = 19
        versionName = "1.0.0-beta.3.1"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
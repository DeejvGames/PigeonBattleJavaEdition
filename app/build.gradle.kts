import java.time.LocalDateTime

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
        versionCode = 25
        versionName = "1.0.0"

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

val devVersionLabel = "-DEV-"+LocalDateTime.now().year+if(LocalDateTime.now().monthValue<10){"0"+LocalDateTime.now().monthValue}else{LocalDateTime.now().monthValue}+if(LocalDateTime.now().dayOfMonth<10){"0"+LocalDateTime.now().dayOfMonth}else{LocalDateTime.now().dayOfMonth}+if(LocalDateTime.now().hour<10){"0"+LocalDateTime.now().hour}else{LocalDateTime.now().hour}+if(LocalDateTime.now().minute<10){"0"+LocalDateTime.now().minute}else{LocalDateTime.now().minute}+if(LocalDateTime.now().second<10){"0"+LocalDateTime.now().second}else{LocalDateTime.now().second} //TODO: FIX THE PROBLEM WITH ZEROS
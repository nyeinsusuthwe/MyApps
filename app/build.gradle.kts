
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "it.jgiem.myapps"
    compileSdk = 34

    defaultConfig {
        applicationId = "it.jgiem.myapps"
        minSdk = 26
        targetSdk = 34
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
    }

    buildFeatures{
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    //implementation 'com.google.android.material:material:1.9.0' // or the latest version
    implementation(libs.constraintlayout)
    implementation(libs.room.compiler.processing.testing)
    implementation(libs.media3.exoplayer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.chanmratekoko:myanmar-calendar:1.0.9.RELEASE")
//    classpath"com.android.tools.build:gradle:8.7"
}
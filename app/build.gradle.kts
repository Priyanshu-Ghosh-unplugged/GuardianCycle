plugins {
    alias(libs.plugins.android.application)


}

android {
    namespace = "com.example.guardiancycle"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.guardiancycle"
        minSdk = 24
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}
    dependencies {

        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.constraintlayout)
        implementation(libs.navigation.fragment)
        implementation(libs.navigation.ui)
        implementation(libs.play.services.location)
        implementation(libs.firebase.database)
        implementation(libs.firebase.auth)
        implementation(libs.firebase.messaging)
        implementation(libs.google.services)
        implementation(libs.com.google.gms.google.services.gradle.plugin)
        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)
        implementation(platform(libs.firebase.bom))
        implementation(libs.firebase.analytics)
    }

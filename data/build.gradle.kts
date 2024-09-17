plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.apollo.graphql)
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

apollo {
    service("service") {
        packageName.set("com.github")
    }
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.toptal.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        val githubToken: String by rootProject.extra
        buildConfigField("String", "GITHUB_TOKEN", "\"${githubToken}\"")
        buildConfigField("String", "GITHUB_API", "\"https://api.github.com/graphql\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.apollo.runtime)
    implementation(libs.logging.interceptor)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.onboarding_presentation"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

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

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(Modules.coreUi))
    implementation(ViewPager.viewPager)
    implementation(ViewPager.viewPagerIndicator)
    // Android Compose
    implementation(Compose.activityCompose)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.material)
    implementation(Compose.viewModelCompose)
    implementation(Compose.navigation)

    // Common lottie , coil
    implementation(Common.lottieCompose)
    implementation(Common.coilCompose)

    // Dagger Hilt
    kapt(DaggerHilt.hiltCompiler)
    implementation (DaggerHilt.hiltAndroid)
    implementation(DaggerHilt.hiltNavigationCompose)

    // Retrofit Network
    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.gsonConvertor)

    // AndroidX
    implementation (AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(Common.materialGoogle)

    // AndroidX Component
    implementation (AndroidX.androidViewModel)
    implementation  (AndroidX.androidViewModelKtx)
    implementation (AndroidX.appCompat)

    // Testing
    testImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.espresso)
    testImplementation (Testing.truth)
    testImplementation (Testing.coroutines)
    testImplementation (Testing.turbine)
    testImplementation (Testing.composeUiTest)
    testImplementation (Testing.mockk)
    androidTestImplementation (Testing.hiltTesting)

}
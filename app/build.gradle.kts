plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("kotlin-parcelize")
  id("androidx.navigation.safeargs")
}

android {
  namespace = "com.example.travalexam"
  compileSdk = 33

  defaultConfig {
    applicationId = "com.example.travalexam"
    minSdk = 28
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildConfigField("String", "BASE_API_URL", "\"https://www.travel.taipei/open-api/\"")

  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    viewBinding = true
    buildConfig = true
  }

}

dependencies {
  implementation("androidx.core:core-ktx:1.10.1")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.9.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.legacy:legacy-support-v4:1.0.0")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  /* DI (Koin) */
  implementation("io.insert-koin:koin-android:3.3.2")

  /* Gson */
  implementation("com.google.code.gson:gson:2.10.1")

  /* Navigation component */
  implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
  implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
  implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

  /* Coroutines*/
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

  /* Retrofit */
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

  /* Room Database */
  kapt("androidx.room:room-compiler:2.5.2")
  implementation("androidx.room:room-runtime:2.5.2")
  implementation("androidx.room:room-ktx:2.5.2")
  implementation("androidx.room:room-paging:2.5.2")

  /* Paging 3 */
  implementation("androidx.paging:paging-runtime-ktx:3.2.0")

  /* Glide */
  implementation("com.github.bumptech.glide:glide:4.15.1")
  kapt("com.github.bumptech.glide:compiler:4.15.1")

}
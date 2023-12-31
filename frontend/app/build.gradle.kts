plugins {
    id("com.android.application")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.farmerapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.farmerapp"
        minSdk = 24
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.airbnb.android:lottie:3.7.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation ("com.google.firebase:firebase-firestore:23.0.3")
    implementation ("com.google.firebase:firebase-auth:21.0.1")
    implementation ("com.google.firebase:firebase-database:20.0.3")
    //newly added
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")

    //prev
    testImplementation("junit:junit:4.13.2")
//    implementation ('com.github.bumptech.glide:glide:4.8.0'
//    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}
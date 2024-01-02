plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

// declare library details for publishing in kts format
ext {
    val mGroupId = "com.github.inferencetime"
    val mArtifactId = "edgetrack-android-sdk"
    val mVersionCode = 1
    val mVersionName = "0.0.1"

    val mLibraryName = "EdgeTrack-Android-SDK"
    val mLibraryDescription = "An Android SDK for collecting performance metrics of machine learning models on edge devices"
}


android {
    namespace = "com.inferencetime.edgetrack"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

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

// publish library details in kts format
    publishing {
        singleVariant("release") {
            withSourcesJar()
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //SDK dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
}

// publish library in kts format
afterEvaluate {
    configure<PublishingExtension> {
        publications.create<MavenPublication>("EdgeTrack-Android-SDK") {
            groupId = "com.github.inferencetime"
            artifactId = "edgetrack-android-sdk"
            version = "0.0.1"
            description = "An Android SDK for collecting performance metrics of machine learning models on edge devices"

            artifact("$buildDir/build/outputs/aar/EdgeTrack-release.aar")

        }
        repositories {
            mavenLocal()
        }
    }
}

// define task for androidSourcesJar in kts format
tasks.register<Jar>("androidSourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}


// define task for androidJavadocsJar in kts format
tasks.register<Jar>("androidJavadocsJar") {
    archiveClassifier.set("javadoc")
    from(android.sourceSets["main"].java.srcDirs)
}




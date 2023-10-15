plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version Deps.kotlinVersion
    id("com.squareup.sqldelight")
}

//@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    //targetHierarchy.default()
    android()
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.ktorCore)
                implementation(Deps.ktorSerialization)
                implementation(Deps.ktorSerializationJson)
                implementation(Deps.sqlDelightRuntime)
                implementation(Deps.sqlDelightCoroutinesExtensions)
                implementation(Deps.kotlinDateTime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Deps.assertK)
                implementation(Deps.turbine)
            }
        }
    }
}

android {
    namespace = "com.gaeunpark.myfristkmmapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
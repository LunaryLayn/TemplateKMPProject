import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
}

kotlin {

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {

            // Compose
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)

            // Navigation
            implementation(libs.decompose.core)
            implementation(libs.decompose.compose)
            implementation(libs.decompose.essenty)
            implementation(libs.decompose.android.extension)
            //implementation(libs.decompose)

            // DI
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            // Database
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines)

            // Network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content)
            implementation(libs.ktor.client.serialization)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // DateTime
            implementation(libs.kotlinx.datetime)

            // Logs
            implementation(libs.napier)

        }

        androidMain.dependencies {

            implementation(libs.androidx.activity.compose)

            // Ktor engine
            implementation(libs.ktor.client.android)

            // SQLDelight driver
            implementation(libs.sqldelight.android)
        }

        iosMain.dependencies {

            // Ktor engine
            implementation(libs.ktor.client.ios)

            // SQLDelight driver
            implementation(libs.sqldelight.ios)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.astrocoders.cooki"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.astrocoders.cooki"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

/**
 * SQLDelight config
 * Va fuera de kotlin{} y android{}
 */
sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.astrocoders.cooki.database")
        }
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}

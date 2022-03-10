import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "com.forever.bee"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Kotlin
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.0")

    // Anko Coroutines
    implementation ("org.jetbrains.anko:anko-coroutines:0.10.8")

    // Reactive programming - RxJava
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
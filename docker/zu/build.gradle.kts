import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.3.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.zookeeper:zookeeper:3.9.5")
}

tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "io.pravega.zookeeper.MainKt"
    }
}

tasks.withType<KotlinCompile> {
  compilerOptions {
    jvmTarget = JvmTarget.fromTarget("17")
  }
}

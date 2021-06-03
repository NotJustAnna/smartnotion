import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    kotlin("multiplatform") version "1.5.10"
    application
}

group = "net.notjustanna"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
}

kotlin {
    js("react", LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    jvm("serverJvm") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()

    }
//    js("serverJs", LEGACY) {
//        binaries.executable()
//        nodejs {
//        }
//    }
    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
        }
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
                implementation("io.ktor:ktor-client-core:1.5.2")
                implementation("org.kodein.di:kodein-di:7.5.0")
            }
        }
        val serverJvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:1.5.2")
                implementation("io.ktor:ktor-client-java:1.5.2")
                implementation("io.ktor:ktor-html-builder:1.5.2")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
                implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:7.5.0")
            }
        }
        val reactMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:5.2.0-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.0-pre.206-kotlin-1.5.10")
                implementation(npm("styled-components", "~5.3.0"))
            }
        }
//        val serverJsMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-js:1.5.2")
//                implementation("org.jetbrains.kotlinx:kotlinx-nodejs:0.0.7")
//                implementation(npm("@types/aws-lambda", "8.10.76",  generateExternals = true))
//            }
//        }
    }
}

application {
    mainClassName = "net.notjustanna.smartnotion.server.ktor.MainKt"
}

tasks.getByName<KotlinWebpack>("reactBrowserProductionWebpack") {
    outputFileName = "react.js"
}

tasks.getByName<Jar>("serverJvmJar") {
    dependsOn(tasks.getByName("reactBrowserProductionWebpack"))
    val reactBrowserProductionWebpack = tasks.getByName<KotlinWebpack>("reactBrowserProductionWebpack")
    from(File(reactBrowserProductionWebpack.destinationDirectory, reactBrowserProductionWebpack.outputFileName))
    from(
        File(
            reactBrowserProductionWebpack.destinationDirectory,
            reactBrowserProductionWebpack.outputFileName + ".map"
        )
    )
}

tasks.getByName<JavaExec>("run") {
    dependsOn(tasks.getByName<Jar>("serverJvmJar"))
    classpath(tasks.getByName<Jar>("serverJvmJar"))
}

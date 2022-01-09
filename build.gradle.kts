plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
    application
    idea
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Common
    implementation("ch.qos.logback:logback-classic:1.2.10")

    // Test
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

version = "1.0.0"

distributions {
    main {
        contents {
            from("src/main/resources/mesh_2v")
        }
    }
}

application {
    mainClass.set("com.romanov.gmsh.AppKt")
}

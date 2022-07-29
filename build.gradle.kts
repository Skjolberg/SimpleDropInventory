plugins {
    java
    id("com.github.johnrengelman.shadow") version ("7.1.2")
}

var libs = "net.shibacraft.simpleblockregen.api.libs"

group = "net.shibacraft.simpledropinventory"
version = "0.0.8-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.simplix-softworks:simplixstorage:3.2.4")
    implementation("org.bstats:bstats-bukkit:3.0.0")
    implementation("me.fixeddev:commandflow-bukkit:0.5.2")
    compileOnly("org.spigotmc:spigot-api:1.19.1-R0.1-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}

tasks {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    shadowJar {
        delete(file("${project.buildDir}"))
        archiveClassifier.set("")
        archiveFileName.set("${project.name}-${project.version}.jar")
        relocate("me.fixeddev.commandflow", "$libs.cmdFlow")
        relocate("org.bstats", "$libs.bStats")
        relocate("com.github.simplix-softworks", "$libs.leonhardStorage")
        minimize()
    }
    build {
        dependsOn(shadowJar)
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("v" to project.version)
        }
    }

}
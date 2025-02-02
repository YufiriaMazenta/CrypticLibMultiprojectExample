plugins {
    id("java-library")
    id("maven-publish")
    id("com.github.johnrengelman.shadow").version("7.1.2")
}

group = rootProject.findProperty("group").toString()
version = rootProject.findProperty("version")!!
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")
    tasks {
        compileJava {
            dependsOn(clean)
            options.encoding = "UTF-8"
        }
        build {
            dependsOn(shadowJar)
        }
        shadowJar {
            relocate("crypticlib", "${rootProject.group}.${rootProject.name.lowercase()}.crypticlib")
            archiveFileName.set("${rootProject.name}-${project.name}-${rootProject.version}.jar")
        }
    }
    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17
}

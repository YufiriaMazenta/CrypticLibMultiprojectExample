repositories {
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.crypticlib.com:8081/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
    implementation("com.crypticlib:bukkit:${rootProject.findProperty("crypticlibVersion")}")
}

tasks {
    val props = HashMap<String, String>()
    props["version"] = rootProject.version.toString()
    props["main"] = rootProject.findProperty("main-bukkit").toString()
    props["name"] = rootProject.name
    processResources {
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
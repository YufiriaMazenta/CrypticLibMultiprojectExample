repositories {
    mavenLocal()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("http://repo.crypticlib.com:8081/repository/maven-public/") {
        isAllowInsecureProtocol = true
    }
    maven("https://libraries.minecraft.net/")
    mavenCentral()
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:1.20-R0.2")
    compileOnly("org.jetbrains:annotations:24.0.1")
    implementation("com.crypticlib:bungee:${rootProject.findProperty("crypticlibVersion")}")
}

tasks {
    val props = HashMap<String, String>()
    props["version"] = rootProject.version.toString()
    props["main"] = rootProject.findProperty("main-bungee").toString()
    props["name"] = rootProject.name
    processResources {
        filesMatching("bungee.yml") {
            expand(props)
        }
    }
}
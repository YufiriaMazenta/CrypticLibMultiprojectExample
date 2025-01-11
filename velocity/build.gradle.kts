plugins {
    id("net.kyori.blossom") version "1.3.1"
}

repositories {
    mavenLocal()
    maven("https://oss.sonatype.org/content/groups/public/")
    //Velocity CrypticLib
    maven("http://repo.crypticlib.com:8081/repository/maven-public/") {
        isAllowInsecureProtocol = true
    }
    maven("https://libraries.minecraft.net/")
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.1")
    compileOnly("com.velocity:velocity:3.4.0")
    implementation("com.crypticlib:velocity:${rootProject.findProperty("crypticlibVersion")}")
}

tasks {
    val props = HashMap<String, String>()
    props["version"] = rootProject.version.toString()
    props["main"] = rootProject.findProperty("main-velocity").toString()
    props["name"] = rootProject.name
    props["id"] = rootProject.name.lowercase()
    processResources {
        filesMatching("velocity-plugin.json") {
            expand(props)
        }
    }
}

blossom {
    replaceToken("{{id}}", rootProject.name.lowercase())
    replaceToken("{{name}}", rootProject.name)
    replaceToken("{{version}}", rootProject.version.toString())
    replaceTokenIn("velocity/src/main/java/com/example/crypticlibexample/velocity/Example.java")
}
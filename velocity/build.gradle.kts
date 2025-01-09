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
    compileJava {
        //创建一个新的目录用于临时存储替换后的文件
        val tempDir = File("$buildDir/tempSources")

        //确保临时目录存在
        doFirst {
            tempDir.mkdirs() //确保目录存在

            //遍历源文件进行替换
            source.files.forEach { file ->
                println(file.path)
                if (file.extension == "java" || file.extension == "kt") {
                    //读取原始文件内容
                    val content = file.readText()

                    //替换内容
                    val updatedContent = content
                        .replace("{{version}}", rootProject.version.toString())
                        .replace("{{name}}", rootProject.name)
                        .replace("{{id}}", rootProject.name.lowercase())

                    //将替换后的内容写入临时目录中，而不是原始文件
                    val tempFile = File(tempDir, file.relativeTo(file.parentFile).path)
                    tempFile.parentFile.mkdirs() // 创建目录
                    tempFile.writeText(updatedContent)
                }
            }

            //将替换后的文件添加到编译任务中
            source = fileTree(tempDir)
        }
        //在构建完成后删除临时文件夹，避免长期存储
        doLast {
            tempDir.deleteRecursively()
        }
    }
}
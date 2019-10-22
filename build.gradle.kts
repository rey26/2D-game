plugins {
    java
    application
    pmd
}

configure<PmdExtension> {
    toolVersion = "6.18.0"
    isConsoleOutput = true
    ruleSets = emptyList()
    ruleSetFiles = files("src/main/resources/pmd-ruleset.xml")
}

group = "sk.tuke.kpi.oop"
version = "1.0"

val gamelibVersion = "2.5.0"

repositories {
    mavenCentral()
    maven(url=uri("https://repo.kpi.fei.tuke.sk/repository/maven-public"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

application {
    mainClassName = "sk.tuke.kpi.gamelib.framework.Main"
}

dependencies {
    implementation("sk.tuke.kpi.gamelib:gamelib-framework:$gamelibVersion")
}

tasks {
    withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-parameters", "-Xlint:unchecked,rawtypes", "-Werror"))
    }
}

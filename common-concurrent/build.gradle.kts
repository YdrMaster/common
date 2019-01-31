import com.novoda.gradle.release.PublishExtension

apply {
    plugin("com.novoda.bintray-release")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.1.1")

    implementation(project(":common-extension"))

    testImplementation("junit", "junit", "4.12")
}

task<Jar>("sourcesJar") {
    classifier = "sources"
    group = "build"

    from(sourceSets["main"].allSource)
}

configure<PublishExtension> {
    userOrg = "mechdancer"
    groupId = "org.mechdancer"
    artifactId = project.name
    publishVersion = version.toString()
    desc = "common concurrent utilities"
    website = "https://github.com/MechDancer/common/$artifactId"
    issueTracker = "https://github.com/MechDancer/common/issues"
    repository = "https://github.com/MechDancer/common.git"
    setLicences("WTFPL")
}

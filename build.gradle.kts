import java.util.Properties
import dev.iurysouza.modulegraph.LinkText
import dev.iurysouza.modulegraph.ModuleType
import dev.iurysouza.modulegraph.Orientation
import dev.iurysouza.modulegraph.Theme

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.module.graph) apply false
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { stream ->
        localProperties.load(stream)
    }
}

extra["githubToken"] = localProperties.getProperty("GITHUB_TOKEN")

//moduleGraphConfig {
//    readmePath.set("./README.md")
//    heading = "### Module Graph"
//    // showFullPath.set(false) // optional
//    // orientation.set(Orientation.LEFT_TO_RIGHT) //optional
//    // linkText.set(LinkText.NONE) // optional
//    // setStyleByModuleType.set(true) // optional
//    // excludedConfigurationsRegex.set(".*test.*") // optional
//    // excludedModulesRegex.set(".*moduleName.*") // optional
//    // focusedModulesRegex.set(".*(projectName).*") // optional
//    // rootModulesRegex.set(".*moduleName.*") // optional
//    // theme.set(Theme.NEUTRAL) // optional
//    // or you can fully customize it by using the BASE theme:
//    // Theme.BASE(
//    //     themeVariables = mapOf(
//    //         "primaryTextColor" to "#F6F8FAff", // Text
//    //         "primaryColor" to "#5a4f7c", // Node
//    //         "primaryBorderColor" to "#5a4f7c", // Node border
//    //         "tertiaryColor" to "#40375c", // Container box background
//    //         "lineColor" to "#f5a623",
//    //         "fontSize" to "12px",
//    //     ),
//    //     focusColor = "#F5A622",
//    //     moduleTypes = listOf(
//    //         ModuleType.AndroidLibrary("#2C4162"),
//    //     )
//    // ),
//    // )
//
//    // You can add additional graphs.
//    // A separate graph will be generated for each config below.
//    // graph(
//    //     readmePath = "./README.md",
//    //     heading = "# Graph with root: gama",
//    // ) {
//    //     rootModulesRegex = ".*gama.*"
//    // }
//    // graph(
//    //     readmePath = "./SomeOtherReadme.md",
//    //     heading = "# Graph",
//    // ) {
//    //     rootModulesRegex = ".*zeta.*"
//    // }
//}
import fuzs.multiloader.extension.commonProject
import fuzs.multiloader.extension.packageName

plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-fabric")
}

dependencies {
    modApi(sharedLibs.fabricapi.fabric)
    modApi(sharedLibs.puzzleslib.fabric)
    compileOnly(sharedLibs.jeiapi.common)
    localRuntime(sharedLibs.jei.fabric)
//    compileOnly(sharedLibs.reiapi.fabric)
//    localRuntime(sharedLibs.bundles.rei.fabric)
}

multiloader {
    modFile {
        json {
            entrypoint(
                "jei_mod_plugin",
                "${project.group}.${project.commonProject.packageName}.integration.jei.ArcaneLanternsJeiPlugin"
            )
//            entrypoint(
//                "rei_common",
//                "${project.group}.${project.commonProject.packageName}.integration.rei.ArcaneLanternsReiPlugin"
//            )
//            entrypoint(
//                "rei_client",
//                "${project.group}.${project.commonProject.packageName}.integration.rei.ArcaneLanternsReiClientPlugin"
//            )
        }
    }
}

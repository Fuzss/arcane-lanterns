plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-neoforge")
}

dependencies {
    modCompileOnly(sharedLibs.puzzleslib.common)
    modApi(sharedLibs.puzzleslib.neoforge)
    modCompileOnly(sharedLibs.jeiapi.common)
    modLocalRuntime(sharedLibs.jei.neoforge)
//    modCompileOnly(sharedLibs.reiapi.neoforge)
//    modLocalRuntime(sharedLibs.bundles.rei.neoforge)
}

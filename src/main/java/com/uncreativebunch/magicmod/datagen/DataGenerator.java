package com.uncreativebunch.magicmod.datagen;

import com.uncreativebunch.magicmod.MagicMod;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.recipe.RecipeManager;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricDataGenerator.Pack pack = dataGenerator.createPack();

        MagicMod.LOGGER.info("Hello from the data generator!");

        // Add providers to the pack here.
        pack.addProvider(StickTagRecipeProvider::new);
    }
}

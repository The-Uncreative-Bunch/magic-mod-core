package com.uncreativebunch.magicmod.datagen;

import com.uncreativebunch.magicmod.MagicMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class StickTagRecipeProvider extends FabricRecipeProvider {
    public StickTagRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup lookup, RecipeExporter exporter) {
        return new RecipeGenerator(lookup, exporter) {
            @Override
            public void generate() {
                MagicMod.LOGGER.info("Hello from the recipe provider!");
                MagicMod.LOGGER.info("This cannot scan recipes - apparently they don't exist in this context.");

                // Create a builder for our custom recipe types. This will allow for type-safe creation of recipes.
                // This builder should likely be defined as a static class or function within the main Recipe class.
                // RecipeBuilder.create(
                //     Ingredient.fromTag(ItemTags.MAGIC_MATERIALS),
                //     Ingredient.fromTag(ItemTags.MAGIC_CORES),
                //     Ingredient.fromTag(ItemTags.MAGIC_GRIPS),
                //     ModItems.BASIC_WAND.getDefaultStack(),
                //     new Identifier("magic_mod", "basic_wand_crafting")
                // );
            }
        };
    }

    @Override
    public String getName() {
        return "StickTagRecipeProvider";
    }
}

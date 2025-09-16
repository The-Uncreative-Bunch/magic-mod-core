package com.uncreativebunch.magicmod.datagen;

import com.uncreativebunch.magicmod.recipe.MagicShapedRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class MagicRecipeProvider extends FabricRecipeProvider {
    public MagicRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                // Recipes are added here. We only want to create MagicShapedRecipes here.
                MagicShapedRecipe.Builder.build(
                        Ingredient.ofItem(Items.STICK),
                        Ingredient.ofItem(Items.STICK),
                        Ingredient.ofItem(Items.STICK),
                        Items.REDSTONE.getDefaultStack()
                ).offerTo(exporter, Identifier.of("magic_mod", "magic_testing_recipe"));
            }
        };
    }

    @Override
    public String getName() {
        return "MagicRecipeProvider";
    }
}

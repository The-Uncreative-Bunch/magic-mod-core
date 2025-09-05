package com.uncreativebunch.magicmod.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipeSerializers {
    public static RecipeSerializer<MagicShapedRecipe> MAGIC_SHAPED = new MagicShapedRecipe.Serializer();

    public static <T extends Recipe<?>> RecipeSerializer<T> register(String id, RecipeSerializer<T> type) {
        return Registry.register(Registries.RECIPE_SERIALIZER, id, type);
    }

    public static void init() {}
}

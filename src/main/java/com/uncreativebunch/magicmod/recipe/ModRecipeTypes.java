package com.uncreativebunch.magicmod.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipeTypes {
    public static final RecipeType<MagicShapedRecipe> MAGIC_SHAPED = register("magic_shaped", MagicShapedRecipe.Type.INSTANCE);

    public static <T extends Recipe<?>> RecipeType<T> register(String id, RecipeType<T> type) {
        return Registry.register(Registries.RECIPE_TYPE, id, type);
    }

    public static void init() {}
}

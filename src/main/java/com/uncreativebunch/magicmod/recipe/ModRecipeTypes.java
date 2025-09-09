package com.uncreativebunch.magicmod.recipe;

import com.uncreativebunch.magicmod.MagicMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {
    public static final RecipeType<MagicShapedRecipe> MAGIC_SHAPED = register("magic_shaped", MagicShapedRecipe.Type.INSTANCE);

    public static <T extends Recipe<?>> RecipeType<T> register(String id, RecipeType<T> type) {
        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(MagicMod.MOD_ID, id), type);
    }

    public static void init() {}
}

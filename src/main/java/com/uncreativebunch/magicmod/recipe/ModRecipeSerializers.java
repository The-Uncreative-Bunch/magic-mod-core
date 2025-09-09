package com.uncreativebunch.magicmod.recipe;

import com.uncreativebunch.magicmod.MagicMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializers {
    public static RecipeSerializer<MagicShapedRecipe> MAGIC_SHAPED = register(
            "magic_shaped",
            MagicShapedRecipe.Serializer.INSTANCE
    );

    /**
     * Registers a {@link RecipeSerializer} with Minecraft's internal registry.
     * @param id The {@link net.minecraft.util.Identifier} of the serializer.
     * @param type An instance of the {@link RecipeSerializer} to register.
     * @return The registered instance of the {@link RecipeSerializer}.
     * @param <T> The type of the {@link Recipe} that is serialized.
     */
    public static <T extends Recipe<?>> RecipeSerializer<T> register(String id, RecipeSerializer<T> type) {
        return Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MagicMod.MOD_ID, id), type);
    }

    public static void init() {}
}

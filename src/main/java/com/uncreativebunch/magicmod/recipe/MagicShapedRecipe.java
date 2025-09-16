package com.uncreativebunch.magicmod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.uncreativebunch.magicmod.MagicMod;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public record MagicShapedRecipe(Ingredient coreIngredient, Ingredient materialIngredient, Ingredient gripIngredient,
                                ItemStack output, Identifier id) implements Recipe<MagicShapedInput> {
    @Override
    public boolean matches(MagicShapedInput input, World world) {
        if (input.stacks().size() < 2) return false;
        return coreIngredient.test(input.stacks().getFirst())
                && materialIngredient.test(input.stacks().get(1))
                && gripIngredient.test(input.stacks().get(2));
    }

    @Override
    public ItemStack craft(MagicShapedInput input, RegistryWrapper.WrapperLookup registries) {
        return this.output.copy();
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return Recipe.super.isIgnoredInRecipeBook();
    }

    @Override
    public boolean showNotification() {
        return Recipe.super.showNotification();
    }

    @Override
    public String getGroup() {
        return Recipe.super.getGroup();
    }

    @Override
    public RecipeSerializer<? extends Recipe<MagicShapedInput>> getSerializer() {
        return ModRecipeSerializers.MAGIC_SHAPED;
    }

    @Override
    public RecipeType<? extends Recipe<MagicShapedInput>> getType() {
        return ModRecipeTypes.MAGIC_SHAPED;
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return Recipe.super.getDisplays();
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        List<Optional<Ingredient>> ingredients = List.of(
                Optional.of(coreIngredient),
                Optional.of(materialIngredient),
                Optional.of(gripIngredient)
        );

        return IngredientPlacement.forMultipleSlots(ingredients);
    }

    // Type definition
    public static class Type implements RecipeType<MagicShapedRecipe> {
        private Type() { }

        public static final Type INSTANCE = new Type();
        public static final String ID = "magic_crafting";
    }

    // Serializer definition
    public static class Serializer implements RecipeSerializer<MagicShapedRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final MapCodec<MagicShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(
                // This function essentially creates a way to deserialize from a JSON using the constructor's
                // parameters, as defined above.
                instance -> instance.group(
                        Ingredient.CODEC.fieldOf("coreIngredient").forGetter(MagicShapedRecipe::coreIngredient),
                        Ingredient.CODEC.fieldOf("materialIngredient").forGetter(MagicShapedRecipe::materialIngredient),
                        Ingredient.CODEC.fieldOf("gripIngredient").forGetter(MagicShapedRecipe::gripIngredient),
                        ItemStack.CODEC.fieldOf("output").forGetter(MagicShapedRecipe::output),
                        Identifier.CODEC.fieldOf("id").forGetter(MagicShapedRecipe::id)
                ).apply(instance, MagicShapedRecipe::new)
        );

        private static final PacketCodec<RegistryByteBuf, MagicShapedRecipe> PACKET_CODEC = PacketCodec.tuple(
                Ingredient.PACKET_CODEC, MagicShapedRecipe::coreIngredient,
                Ingredient.PACKET_CODEC, MagicShapedRecipe::materialIngredient,
                Ingredient.PACKET_CODEC, MagicShapedRecipe::gripIngredient,
                ItemStack.PACKET_CODEC, MagicShapedRecipe::output,
                Identifier.PACKET_CODEC, MagicShapedRecipe::id,
                MagicShapedRecipe::new
        );

        /**
         * Gets the codec for this serializer. This determines how the recipe is parsed from JSON.
         *
         * @return The codec for this serializer.
         */
        @Override
        public MapCodec<MagicShapedRecipe> codec() {
            return CODEC;
        }

        @Override
        @Deprecated
        public PacketCodec<RegistryByteBuf, MagicShapedRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    // Builder definition
    public record Builder(Ingredient core, Ingredient material, Ingredient grip, ItemStack output) {
        /**
         * Creates a new {@link Builder}.
         * @param core The core {@link Ingredient}.
         * @param material The material {@link Ingredient}.
         * @param grip The grip {@link Ingredient}.
         * @param output The output {@link ItemStack}
         * @return A new {@link Builder} that can be offered to a {@link RecipeExporter}.
         */
        public static Builder build(
                Ingredient core,
                Ingredient material,
                Ingredient grip,
                ItemStack output
        ) {
            return new Builder(core, material, grip, output);
        }

        /**
         * Offers the contents of this builder to the given {@link RecipeExporter}.
         * @param exporter The {@link RecipeExporter} to use.
         * @param recipeId The {@link Identifier} of the recipe to create.
         */
        public void offerTo(RecipeExporter exporter, Identifier recipeId) {
            RegistryKey<Recipe<?>> key = RegistryKey.of(RegistryKeys.RECIPE, recipeId);
            MagicShapedRecipe recipe = new MagicShapedRecipe(core, material, grip, output, recipeId);
            exporter.accept(key, recipe, null);
            MagicMod.LOGGER.info("Created recipe with id {}", recipeId.toString());
        }
    }
}

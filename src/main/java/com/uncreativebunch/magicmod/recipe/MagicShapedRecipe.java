package com.uncreativebunch.magicmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MagicShapedRecipe implements Recipe<MagicShapedInput> {
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final ItemStack output;
    private final Identifier id;

    public MagicShapedRecipe(Ingredient inputA, Ingredient inputB, ItemStack output, Identifier id) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.output = output;
        this.id = id;
    }

    public Ingredient getInputA() {
        return this.inputA;
    }

    public Ingredient getInputB() {
        return inputB;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public Identifier getId() {
        return this.id;
    }

    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public boolean matches(MagicShapedInput input, World world) {
        if (input.stacks().size() < 2) return false;
        return inputA.test(input.stacks().getFirst()) && inputB.test(input.stacks().get(1));
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
        return null;
    }

    // Implement the recipe's type.
    public static class Type implements RecipeType<MagicShapedRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "magic_crafting";
    }

    /**
     * {@return the type of this recipe}
     *
     * <p>The {@code type} in the recipe JSON format is the {@linkplain
     * #getSerializer() serializer} instead.
     */
    @Override
    public RecipeType<? extends Recipe<MagicShapedInput>> getType() {
        return ModRecipeTypes.MAGIC_SHAPED;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return null;
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return Recipe.super.getDisplays();
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    // Serializer definition
    public static class Serializer implements RecipeSerializer<MagicShapedRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final MapCodec<MagicShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(
            // This function essentially creates a way to deserialize from a JSON using the constructor's
            // parameters, as defined above.
            instance -> instance.group(
                Ingredient.CODEC.fieldOf("inputA").forGetter(MagicShapedRecipe::getInputA),
                Ingredient.CODEC.fieldOf("inputB").forGetter(MagicShapedRecipe::getInputB),
                ItemStack.CODEC.fieldOf("output").forGetter(MagicShapedRecipe::getOutput),
                Identifier.CODEC.fieldOf("id").forGetter(MagicShapedRecipe::getId)
            ).apply(instance, MagicShapedRecipe::new)
        );

        private static final PacketCodec<RegistryByteBuf, MagicShapedRecipe> PACKET_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, MagicShapedRecipe::getInputA,
            Ingredient.PACKET_CODEC, MagicShapedRecipe::getInputB,
            ItemStack.PACKET_CODEC, MagicShapedRecipe::getOutput,
            Identifier.PACKET_CODEC, MagicShapedRecipe::getId,
            MagicShapedRecipe::new
        );

        /**
         * Gets the codec for this serializer. This determines how the recipe is parsed from JSON.
         * @return The codec for this serializer.
         */
        @Override
        public MapCodec<MagicShapedRecipe> codec() {
            return CODEC;
        }

        /**
         * Gets the packet codec for this serializer. This determines how the recipe is parsed from packets.
         * @return The packet codec for this serializer.
         */
        @Override
        public PacketCodec<RegistryByteBuf, MagicShapedRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}

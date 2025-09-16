package com.uncreativebunch.magicmod.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.List;

public record MagicShapedInput(List<ItemStack> stacks) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int index) {
        return stacks.get(index);
    }

    @Override
    public int size() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return RecipeInput.super.isEmpty();
    }
}

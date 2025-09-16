package com.uncreativebunch.magicmod.inventory;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;

public class InputInventory extends SimpleInventory {
    public InputInventory(int size) {
        super(size);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return true;
    }

    /**
     * Executes basic crafting logic and decrements all input stacks.
     */
    public void onCrafted() {
        for (var stack : this) {
            stack.decrement(1);
        }
    }
}

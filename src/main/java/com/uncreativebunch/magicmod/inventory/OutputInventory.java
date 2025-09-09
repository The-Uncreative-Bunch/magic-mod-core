package com.uncreativebunch.magicmod.inventory;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class OutputInventory extends SimpleInventory {

    public OutputInventory(int size) {
        super(size);
    }

    public OutputInventory(ItemStack... items) {
        super(items);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    public boolean canAddStack(ItemStack stack) {
        return super.canInsert(stack);
    }
}

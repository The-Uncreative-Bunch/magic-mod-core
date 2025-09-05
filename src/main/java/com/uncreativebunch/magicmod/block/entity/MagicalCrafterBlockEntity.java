package com.uncreativebunch.magicmod.block.entity;

import com.uncreativebunch.magicmod.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class MagicalCrafterBlockEntity extends BlockEntity implements ImplementedInventory {
    protected DefaultedList<ItemStack> items = DefaultedList.ofSize(3, ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    public MagicalCrafterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.MAGICAL_CRAFTING_TABLE, pos, state);
    }
}

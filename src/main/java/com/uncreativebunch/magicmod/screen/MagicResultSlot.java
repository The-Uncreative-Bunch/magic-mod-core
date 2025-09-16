package com.uncreativebunch.magicmod.screen;

import com.uncreativebunch.magicmod.inventory.InputInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class MagicResultSlot extends Slot {
    /**
     * The crafting input inventory. There is not a current need for custom logic, but this may come in the future.
     */
    protected InputInventory inputInventory;

    public MagicResultSlot(InputInventory inputInventory, Inventory outputInventory, int index, int x, int y) {
        super(outputInventory, index, x, y);
        this.inputInventory = inputInventory;
    }

    /**
     * Runs when the player takes the item out of the slot.
     * This slot will run the crafting logic of the {@link InputInventory}.
     * @param player The player that took the item.
     * @param stack The item that was taken.
     */
    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        this.inputInventory.onCrafted();

        super.onTakeItem(player, stack);
    }

    /**
     * Returns false, as the result slot does not allow items to be inserted.
     * @param stack The item to insert.
     * @return False.
     */
    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }
}

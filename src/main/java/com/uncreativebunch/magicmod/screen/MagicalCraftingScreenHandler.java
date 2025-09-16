package com.uncreativebunch.magicmod.screen;

import com.uncreativebunch.magicmod.inventory.InputInventory;
import com.uncreativebunch.magicmod.inventory.OutputInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MagicalCraftingScreenHandler extends ScreenHandler {

    private final InputInventory inputInventory;
    private final OutputInventory outputInventory;

    /**
     * Client-side constructor.
     * @param syncId The sync ID that's sent from the server.
     * @param playerInventory The player's inventory.
     */
    public MagicalCraftingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new InputInventory(3), new OutputInventory(1));
    }

    /**
     * Server-side constructor. The server knows the player's inventory and so can provide it to the client.
     * It handles creating the inventory slots and syncs all information from the server to the client.
     * @param syncId The sync ID that's sent from the server.
     * @param playerInventory The player's inventory.
     * @param inputInventory The crafting inventory.
     * @param outputInventory The output inventory.
     */
    public MagicalCraftingScreenHandler(int syncId, PlayerInventory playerInventory, InputInventory inputInventory,
                                        OutputInventory outputInventory) {
        super(ModScreenHandlers.MAGICAL_CRAFTING_SCREEN_HANDLER, syncId);
        this.inputInventory = inputInventory;
        this.outputInventory = outputInventory;

        // Ensure that the crafting inventory is the correct size.
        checkSize(inputInventory, 3);
        checkSize(outputInventory, 1);

        // Ensure that the inventory's custom logic runs (if any)
        inputInventory.onOpen(playerInventory.player);
        outputInventory.onOpen(playerInventory.player);

        // Place all inventory slots within the screen. This piece doesn't actually render the background of the slots,
        // as this is handled by the client-side application (the Screen), but it does tell the screen where to render.
        // We do assign locations - each slot is 18 units wide/tall.
        int m, l;
        for (l = 0; l < 3; l++) {
            // Create the crafting slots. This is good for a 3x3 grid of crafting slots. It registers them to a
            // specific inventory and location on the client's screen, relative to the screen's size and origin.
            this.addSlot(new Slot(inputInventory, l, 62 + l * 18, 17));
        }

        // TODO I don't actually know where the output slot should be located. Get correct coords.
        this.addSlot(new MagicResultSlot(inputInventory, outputInventory, 0, 80, 35));

        // Add all player inventory slots. Start with the main inventory, then the hotbar.
        for (m = 0; m < 3; m++) {
            for (l = 0; l < 9; l++) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; m++) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    /**
     * Checks if the specified slot index is within the crafting inventory.
     * @param slotIndex The slot index to check.
     * @return True if the slot index is within the crafting inventory, false otherwise.
     */
    protected boolean isCraftingInventorySlot(int slotIndex) {
        return slotIndex < this.inputInventory.size();
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        // If the slot is in the crafting inventory, move it to the player's inventory.
        // Otherwise, don't move it at all.
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot.hasStack()) {
            ItemStack stack = slot.getStack();
            newStack = stack.copy();
            if (isCraftingInventorySlot(slotIndex)) {
                // Attempt to move the stack into the player's inventory.
                if (!this.insertItem(stack, this.inputInventory.size() + this.outputInventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Don't move the stack.
                return ItemStack.EMPTY;
            }

            // Check if the stack was moved. If moved entirely, empty the slot.
            if (stack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inputInventory.canPlayerUse(player);
    }
}

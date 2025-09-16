package com.uncreativebunch.magicmod.block.entity;

import com.uncreativebunch.magicmod.inventory.InputInventory;
import com.uncreativebunch.magicmod.inventory.OutputInventory;
import com.uncreativebunch.magicmod.recipe.MagicShapedInput;
import com.uncreativebunch.magicmod.recipe.ModRecipeTypes;
import com.uncreativebunch.magicmod.recipe.MagicShapedRecipe;
import com.uncreativebunch.magicmod.screen.MagicalCraftingScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class MagicalCrafterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    private final ServerRecipeManager.MatchGetter<MagicShapedInput, ? extends MagicShapedRecipe> recipeGetter;

    /**
     * The input inventory for crafting ingredients. It goes in the order core, material, and grip.
     */
    private final InputInventory inputInventory = new InputInventory(3);
    /**
     * The output inventory for crafting ingredients.
     */
    private final OutputInventory outputInventory = new OutputInventory(1);

    public MagicalCrafterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.MAGICAL_CRAFTING_TABLE, pos, state);
        this.recipeGetter = ServerRecipeManager.createCachedMatchGetter(ModRecipeTypes.MAGIC_SHAPED);
    }

    /**
     * Gets the input inventory for this block entity.
     * @return A {@link InputInventory} that represents the input inventory.
     */
    public InputInventory getInputInventory() { return inputInventory; }

    /**
     * Gets the output inventory for this block entity.
     * @return An {@link OutputInventory} that represents the output slot.
     */
    public OutputInventory getOutputInventory() { return outputInventory; }

    /**
     * The ticking logic. This will check if the block's inventory contains the correct items to craft
     * a {@link MagicShapedRecipe}.
     * @param world The {@link World} instance.
     * @param pos The {@link BlockPos} positional data.
     * @param state The {@link BlockState} of the block.
     * @param blockEntity An instance of this type.
     */
    public static void tick(World world, BlockPos pos, BlockState state, MagicalCrafterBlockEntity blockEntity) {
        // Check if the recipe within the crafting inventory matches any recipes. Must happen on server side.
        if (world instanceof ServerWorld serverWorld) {
            Optional<? extends RecipeEntry<? extends MagicShapedRecipe>> matched = blockEntity.recipeGetter
                    .getFirstMatch(
                            new MagicShapedInput(blockEntity.inputInventory.getHeldStacks()),
                            serverWorld
                    );

            if (matched.isPresent()) {
                // Work with the RecipeEntry here
                MagicShapedRecipe entry = matched.get().value();
                blockEntity.outputInventory.heldStacks.set(0, entry.output());
            }
        }
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new MagicalCraftingScreenHandler(syncId, playerInventory, inputInventory, outputInventory);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }
}

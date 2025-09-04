package com.uncreativebunch.magicmod.block.crafting;

import com.uncreativebunch.magicmod.MagicMod;
import com.uncreativebunch.magicmod.screen.MagicalCraftingScreenHandler;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagicalCrafterBlock extends CraftingTableBlock {

    /**
     * Creates a new MagicalCrafterBlock.
     * @param settings The settings of the block.
     */
    public MagicalCrafterBlock(AbstractBlock.Settings settings) { super(settings); }

    /**
     * Occurs whenever this block is used.
     * It opens a crafting window and summons lightning on the interacting player.
     * @param state The current {@link BlockState} of this block.
     * @param world The {@link World} that this block exists in.
     * @param pos The {@link BlockPos} that this block is at.
     * @param player The {@link PlayerEntity} that used this object.
     * @param hit The {@link BlockHitResult} that contains information about this interaction.
     * @return Whether the action passed, was canceled, or succeeded.
     */
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // The server is the game's sole source of truth. Even though some things are displayed
        // on the client, these actions usually, with very few exceptions, are routed and triggered
        // through the server first.
        if (!world.isClient) {
            MagicMod.LOGGER.info("Block was used at location {}", pos.toShortString());

            MagicMod.LOGGER.info("Electrocuting player...");
            BlockPos playerPos = player.getBlockPos();
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPosition(playerPos.toCenterPos());
            world.spawnEntity(lightningEntity);
            MagicMod.LOGGER.info("Player electrocuted.");

            NamedScreenHandlerFactory screenFactory = state.createScreenHandlerFactory(world, pos);
            if (screenFactory != null) {
                player.openHandledScreen(screenFactory);
                player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            } else {
                MagicMod.LOGGER.info("NamedScreenHandlerFactory returned from the BlockState is null.");
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory(
            ((syncId, playerInventory, player) ->
                new MagicalCraftingScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos))),
            Text.translatable("container.magical_crafting_table")
        );
    }
}

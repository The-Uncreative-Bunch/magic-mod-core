package com.uncreativebunch.magicmod.block.crafting;

import com.uncreativebunch.magicmod.MagicMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagicalCrafterBlock extends SlabBlock {

    /**
     * Creates a new MagicalCrafterBlock.
     * @param settings The settings of the block.
     */
    public MagicalCrafterBlock(AbstractBlock.Settings settings) { super(settings); }

    /**
     * Occurs whenever this block is used. This is intended to open a crafting window in the future.
     * @param state The current {@link BlockState} of this block.
     * @param world The {@link World} that this block exists in.
     * @param pos The {@link BlockPos} that this block is at.
     * @param player The {@link PlayerEntity} that used this object.
     * @param hit The {@link BlockHitResult} that contains information about this interaction.
     * @return Whether the action passed, was canceled, or succeeded.
     */
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;

        MagicMod.LOGGER.info("Block was used at location {}", pos.toShortString());

        MagicMod.LOGGER.info("Electrocuting player...");
        BlockPos playerPos = player.getBlockPos();
        LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightningEntity.setPosition(playerPos.toCenterPos());
        world.spawnEntity(lightningEntity);
        MagicMod.LOGGER.info("Player electrocuted.");

        return ActionResult.SUCCESS;
    }
}

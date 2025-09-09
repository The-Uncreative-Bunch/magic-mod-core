package com.uncreativebunch.magicmod.block.crafting;

import com.mojang.serialization.MapCodec;
import com.uncreativebunch.magicmod.MagicMod;
import com.uncreativebunch.magicmod.block.entity.MagicalCrafterBlockEntity;
import com.uncreativebunch.magicmod.block.entity.ModBlockEntityTypes;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagicalCrafterBlock extends BlockWithEntity {

    private static final MapCodec<MagicalCrafterBlock> CODEC = null;

    /**
     * Creates a new MagicalCrafterBlock.
     * @param settings The settings of the block.
     */
    public MagicalCrafterBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(SlabBlock.TYPE, SlabType.BOTTOM)
                .with(SlabBlock.WATERLOGGED, false)
        );
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

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
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MagicalCrafterBlockEntity(pos, state);
    }

    /**
     * Adds properties to the block state. This crafting table is a slab, so it has a type and waterlogged property.
     * @param builder The {@link StateManager.Builder} to add properties to.
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SlabBlock.TYPE, SlabBlock.WATERLOGGED);
    }

    /**
     * Creates a {@link BlockEntityTicker} that runs the specified "tick" function from the
     * given {@link BlockEntityType}.
     * @param world The {@link World} instance.
     * @param state The {@link BlockState} of the block.
     * @param type The {@link BlockEntityType} to run the tick function from.
     * @return A new {@link BlockEntityTicker} that runs the specified tick function.
     * @param <T> The {@link BlockEntity} to use.
     */
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntityTypes.MAGICAL_CRAFTING_TABLE, MagicalCrafterBlockEntity::tick);
    }
}

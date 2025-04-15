package com.uncreativebunch.magicmod.block;

import com.uncreativebunch.magicmod.MagicMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    //region Block Definitions

    public static final Block MAGICAL_CRAFTING_TABLE = register(
        "magical_crafting_table",
        Block::new,
        AbstractBlock.Settings
            .create()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK),
        true
    );

    //endregion

    /**
     * Used to statically initialize this class. Nothing needs to be in it for now.
     */
    public static void init() {
        MagicMod.LOGGER.info("Block initialization complete");
    }

    /**
     * Registers a new {@link Block}.
     * @param name The name of the {@link Block} to register.
     * @param factory The block factory to use - typically Block::new
     * @param settings The {@link AbstractBlock.Settings} to use for creation.
     * @param shouldRegisterItem Determines if an item should be created with the block, for use in the inventory.
     * @return The newly registered {@link Block}.
     */
    public static Block register(
        String name,
        Function<AbstractBlock.Settings,Block> factory,
        AbstractBlock.Settings settings,
        boolean shouldRegisterItem) {

        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = factory.apply(settings.registryKey(blockKey));

        // We don't always want to create items for blocks - sometimes the block is in an "active" state,
        // which we don't want to create an item instance for, just the block.
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        // Finally return the block we want to register.
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    /**
     * Gets a {@link RegistryKey} for the specified block name.
     * @param name The name of the {@link Block} to use.
     * @return The {@link RegistryKey} for the specified {@link Block}.
     */
    public static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MagicMod.MOD_ID, name));
    }

    /**
     * Gets a {@link RegistryKey} for the specified item name.
     * @param name The name of the {@link Item} to use.
     * @return The {@link RegistryKey} of the specified {@link Item}.
     */
    public static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MagicMod.MOD_ID, name));
    }
}

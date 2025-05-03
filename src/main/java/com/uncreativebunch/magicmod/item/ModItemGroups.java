package com.uncreativebunch.magicmod.item;

import com.uncreativebunch.magicmod.MagicMod;
import com.uncreativebunch.magicmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Contains all the {@link ItemGroup} objects in this mod.
 * These are what group the items added to the mod within the inventory interface.
 */
public class ModItemGroups {
    /**
     * The registry key for the MAGIC_GROUP.
     */
    public static final RegistryKey<ItemGroup> MAGIC_GROUP_KEY =
        RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),  // We want an ItemGroup key, not a key for an Item, Block, etc.
            Identifier.of(MagicMod.MOD_ID, "magic")  // This just says we're making it for our mod and gives
                                                          // a specific id to our group.
        );

    /**
     * The item group that contains all magical items.
     */
    public static final ItemGroup MAGIC_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(ModItems.TESTING_POWDER))  // The item to show as the group's icon. Can be anything.
        .displayName(Text.translatable("magic_mod.magic_group"))  // Gets the name from the translation file in the mod resources.
        .build();


    /**
     * Handles first-time initialization.
     * Must be called in order for all ItemGroups to be usable in game.
     * This will also initialize the {@link ModItems} class in order to register all
     * custom items.
     */
    public static void init() {
        Registry.register(Registries.ITEM_GROUP, MAGIC_GROUP_KEY, MAGIC_GROUP);
        ItemGroupEvents.modifyEntriesEvent(MAGIC_GROUP_KEY)
            .register(itemGroup -> {
                itemGroup.add(ModItems.ACACIA_STICK);
                itemGroup.add(ModItems.BAMBOO_STICK);
                itemGroup.add(ModItems.BIRCH_STICK);
                itemGroup.add(ModItems.CHERRY_STICK);
                itemGroup.add(ModItems.CRIMSON_STICK);
                itemGroup.add(ModItems.DARK_OAK_STICK);
                itemGroup.add(ModItems.JUNGLE_STICK);
                itemGroup.add(ModItems.MANGROVE_STICK);
                itemGroup.add(ModItems.OAK_STICK);
                itemGroup.add(ModItems.PALE_OAK_STICK);
                itemGroup.add(ModItems.SPRUCE_STICK);
                itemGroup.add(ModItems.WARPED_STICK);
                itemGroup.add(ModItems.TESTING_POWDER);
              
                itemGroup.add(ModBlocks.MAGICAL_CRAFTING_TABLE.asItem());
            });

        MagicMod.LOGGER.info("Item group initialization complete");
    }
}

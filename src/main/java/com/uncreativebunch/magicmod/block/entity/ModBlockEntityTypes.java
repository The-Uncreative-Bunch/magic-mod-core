package com.uncreativebunch.magicmod.block.entity;

import com.uncreativebunch.magicmod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntityTypes {

    public static final BlockEntityType<MagicalCrafterBlockEntity> MAGICAL_CRAFTING_TABLE = register(
        "magical_crafting_table",
        FabricBlockEntityTypeBuilder.create(MagicalCrafterBlockEntity::new, ModBlocks.MAGICAL_CRAFTING_TABLE).build()
    );

    public static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, type);
    }

    public static void init() {}
}

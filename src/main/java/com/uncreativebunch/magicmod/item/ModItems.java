package com.uncreativebunch.magicmod.item;

import com.uncreativebunch.magicmod.MagicMod;
import com.uncreativebunch.magicmod.component.ModConsumableComponents;
import com.uncreativebunch.magicmod.component.ModFoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ModItems {

    public static final Item TESTING_POWDER = register(
        "testing_powder",
        Item::new,
        new Item.Settings()
            .food(ModFoodComponents.TESTING_POWDER_COMPONENT, ModConsumableComponents.TESTING_POWDER_NIGHTVIS)
    );


    public static Item register(String name, @NotNull Function<Item.Settings, Item> factory, Item.Settings settings) {
        // Create the item's registry key (get it from the ITEM registry)
        // Create the item with the specified settings and give it the correct registry key
        // Register the item with the registry (use the key we found and associate it with the new item)

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MagicMod.MOD_ID, name));
        Item item = factory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    /**
     * Handles first-time initialization.
     * Must be called in order for all the item instances to be registered
     * and used within the game.
     */
    public static void init() {
        MagicMod.LOGGER.info("Modded item initialization complete");
    }
}

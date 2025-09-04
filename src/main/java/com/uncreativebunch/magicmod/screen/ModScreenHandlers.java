package com.uncreativebunch.magicmod.screen;

import com.uncreativebunch.magicmod.MagicMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static final ScreenHandlerType<MagicalCraftingScreenHandler> MAGICAL_CRAFTING_SCREEN_HANDLER =
        register("magical_crafting", MagicalCraftingScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }

    public static void init() {
        MagicMod.LOGGER.info("Screen handlers initialized.");
    }
}

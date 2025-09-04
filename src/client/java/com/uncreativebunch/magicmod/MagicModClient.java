package com.uncreativebunch.magicmod;

import com.uncreativebunch.magicmod.screen.MagicalCraftingScreen;
import com.uncreativebunch.magicmod.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MagicModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        HandledScreens.register(ModScreenHandlers.MAGICAL_CRAFTING_SCREEN_HANDLER, MagicalCraftingScreen::new);
	}
}
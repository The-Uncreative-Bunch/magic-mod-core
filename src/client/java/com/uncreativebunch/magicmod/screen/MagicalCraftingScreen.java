package com.uncreativebunch.magicmod.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MagicalCraftingScreen extends HandledScreen<MagicalCraftingScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of("textures/gui/container/anvil.png");

    public MagicalCraftingScreen(MagicalCraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE,
            this.x, (this.height - this.backgroundHeight) / 2,
            0.0F, 0.0F,
            this.backgroundWidth, this.backgroundHeight,
            256, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Centers the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}

package net.sparkshardmc.monstersvsgov.client.screen;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;

public class EntryScreen extends Screen {
    public EntryScreen() {
        // This sets the window title for accessibility/narrators
        super(Component.literal("Choose Your Identity"));
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int x = this.width / 2 - buttonWidth / 2;
        int y = this.height / 2 - 40;

        // Button 1: JOIN MONSTERS (ID 1)
        this.addRenderableWidget(Button.builder(Component.literal("Join Monsters"), (button) -> {
            // Sends the custom payload we defined in MonstersVsGov
            ClientPlayNetworking.send(new MonstersVsGov.FactionPayload(1));
            this.onClose();
        }).bounds(x, y, buttonWidth, 20).build());

        // Button 2: JOIN GOVERNMENT (ID 2)
        this.addRenderableWidget(Button.builder(Component.literal("Join Government"), (button) -> {
            ClientPlayNetworking.send(new MonstersVsGov.FactionPayload(2));
            this.onClose();
        }).bounds(x, y + 30, buttonWidth, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        // Draws the standard dark blurred background
        this.renderBackground(graphics, mouseX, mouseY, delta);
        
        // Draw centered title text
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        // Set to false if you want to FORCE players to pick a side
        return false;
    }
}

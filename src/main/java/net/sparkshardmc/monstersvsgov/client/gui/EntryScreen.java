package net.sparkshardmc.monstersvsgov.client.screen;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;

public class EntryScreen extends Screen {
    public EntryScreen() {
        super(Component.literal("Choose Your Identity"));
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int x = this.width / 2 - buttonWidth / 2;
        int y = this.height / 2 - 40;

        // Button 1: JOIN MONSTERS (Faction ID 1)
        // This triggers giveMonsterStarterGear on the server
        this.addRenderableWidget(Button.builder(Component.literal("Join Monsters"), (button) -> {
            ClientPlayNetworking.send(new MonstersVsGov.FactionPayload(1));
            this.onClose();
        }).bounds(x, y, buttonWidth, 20).build());

        // Button 2: JOIN GOVERNMENT (Faction ID 2)
        // This triggers giveGovStarterGear on the server
        this.addRenderableWidget(Button.builder(Component.literal("Join Government"), (button) -> {
            ClientPlayNetworking.send(new MonstersVsGov.FactionPayload(2));
            this.onClose();
        }).bounds(x, y + 30, buttonWidth, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        // In 26.1.2, renderBackground requires these specific parameters
        this.renderBackground(graphics, mouseX, mouseY, delta);
        
        // Title Rendering
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        
        // Decorative sub-text
        graphics.drawCenteredString(this.font, Component.literal("Select a side to receive your starter gear"), this.width / 2, 60, 0xAAAAAA);
        
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        // Set to false: Players cannot exit this screen until they choose a faction
        return false;
    }
}

package net.sparkshardmc.monstersvsgov.client.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import java.util.ArrayList;
import java.util.List;

public class EntryScreen extends Screen {

    private final List<String> terminalLines = new ArrayList<>();
    private int idleTicks = 0;
    private int selectedFaction = 0;
    private float spaceHoldTime = 0.0f;
    private final float maxHoldTime = 40.0f;
    private float glitchTimer = 0;

    public EntryScreen() {
        super(Component.literal("SYSTEM_INIT"));
    }

    @Override
    protected void init() {
        this.terminalLines.clear();
        this.terminalLines.add("§8[0.00] net.sparkshardmc.java initialized.");
        this.terminalLines.add("§8[0.05] Checking player_data... §4NOT FOUND.");
        this.terminalLines.add("§8[0.10] §eCRITICAL: §fEntity lacks faction credentials.");
    }

    @Override
    public void tick() {
        idleTicks++;

        if (idleTicks == 300) {
            this.terminalLines.add("§c[?] §7Hello? Is this thing on?");
        } else if (idleTicks == 600) {
            this.terminalLines.add("§c[!] §fChoosing \"Nothing\" is not an option. Pick a side.");
        } else if (idleTicks == 1200) {
            this.terminalLines.add("§4[FATAL] §7System timeout approaching. MAKE YOUR CHOICE.");
        } else if (idleTicks == 2400) {
            this.terminalLines.add("§k§4XXX §4FORCING UPLINK §k§4XXX");
            forceAssign();
        }

        if (selectedFaction != 0 && Minecraft.getInstance().options.jumpKey.isDown()) {
            spaceHoldTime++;
            if (spaceHoldTime >= maxHoldTime) {
                finalizeSelection();
            }
        } else {
            spaceHoldTime = Math.max(0, spaceHoldTime - 2);
        }
        
        glitchTimer = Math.max(0, glitchTimer - 1);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int xMid = this.width / 2;
        int yMid = this.height / 2;
        
        int bgColor = 0xCC000000;
        if (idleTicks > 1200) bgColor = 0xEE220000;
        graphics.fill(0, 0, this.width, this.height, bgColor);

        for (int i = 0; i < terminalLines.size(); i++) {
            graphics.drawString(this.font, terminalLines.get(i), 10, 10 + (i * 10), 0xFFFFFF);
        }

        int xOff = (glitchTimer > 0) ? (int)(Math.random() * 10 - 5) : 0;

        renderFactionBox(graphics, xMid - 155 + xOff, yMid - 50, 140, 100, "§2§l[ MONSTERS ]", "§aTHE OVERWORLD IS OURS.", selectedFaction == 1);
        renderFactionBox(graphics, xMid + 15 + xOff, yMid - 50, 140, 100, "§1§l[ GOVERNMENT ]", "§bORDER SHALL BE RESTORED.", selectedFaction == 2);

        if (selectedFaction != 0) {
            graphics.drawCenteredString(this.font, "§fHOLD §n[SPACE]§f TO PERMANENTLY COMMIT", xMid, yMid + 70, 0xFFFFFF);
            float progress = spaceHoldTime / maxHoldTime;
            graphics.fill(xMid - 50, yMid + 85, xMid + 50, yMid + 87, 0xFF333333);
            graphics.fill(xMid - 50, yMid + 85, xMid - 50 + (int)(100 * progress), yMid + 87, 0xFFFFFFFF);
        }
        
        graphics.drawString(this.font, "§8X_PTR:" + mouseX + " Y_PTR:" + mouseY, this.width - 100, this.height - 15, 0xFFFFFF);
    }

    private void renderFactionBox(GuiGraphics g, int x, int y, int w, int h, String title, String sub, boolean sel) {
        int border = sel ? 0xFFFFFFFF : 0xFF444444;
        g.renderOutline(x, y, w, h, border);
        if (sel) g.fill(x, y, x + w, y + h, 0x22FFFFFF);
        g.drawString(this.font, title, x + 10, y + 15, 0xFFFFFF);
        g.drawString(this.font, "§7" + sub, x + 10, y + 35, 0xFFFFFF);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int xMid = this.width / 2;
        int yMid = this.height / 2;

        if (mouseX >= xMid - 155 && mouseX <= xMid - 15 && mouseY >= yMid - 50 && mouseY <= yMid + 50) {
            selectedFaction = 1;
            this.terminalLines.add("§2[SCAN] §aORGANIC DECAY DETECTED. HIVE-MIND READY.");
            glitchTimer = 5;
            return true;
        }
        if (mouseX >= xMid + 15 && mouseX <= xMid + 155 && mouseY >= yMid - 50 && mouseY <= yMid + 50) {
            selectedFaction = 2;
            this.terminalLines.add("§1[SCAN] §bENCRYPTION FOUND. CLEARANCE LEVEL 4.");
            glitchTimer = 5;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    private void forceAssign() {
        selectedFaction = (int)(Math.random() * 2) + 1;
        finalizeSelection();
    }

    private void finalizeSelection() {
        this.minecraft.setScreen(null);
    }
}

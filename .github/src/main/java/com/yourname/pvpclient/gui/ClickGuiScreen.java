package com.yourname.pvpclient.gui;

import com.yourname.pvpclient.module.Category;
import com.yourname.pvpclient.module.Module;
import com.yourname.pvpclient.module.ModuleManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ClickGuiScreen extends Screen {

    private Category selected = Category.VISUALS;
    private final int sidebarWidth = 120;
    private final int panelX = 150;
    private final int panelY = 40;

    public ClickGuiScreen() {
        super(Component.literal("PvP Client"));
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        // Dark semi-transparent background
        g.fill(0, 0, this.width, this.height, 0xCC0A0A12);

        // Sidebar background
        g.fill(20, 30, 20 + sidebarWidth, this.height - 30, 0xFF12121A);

        // Title
        g.drawString(this.font, "PvP Client", 35, 45, 0xFFBB86FC);
        g.drawString(this.font, "26.2", 35, 58, 0xFF666677);

        // Categories
        int y = 85;
        for (Category cat : Category.values()) {
            boolean hover = mouseX >= 30 && mouseX <= 130 && mouseY >= y && mouseY <= y + 24;
            boolean sel = cat == selected;

            int bg = sel ? 0xFF2E1A4A : (hover ? 0xFF1E1E2A : 0xFF16161E);
            g.fill(30, y, 130, y + 24, bg);

            int textColor = sel ? 0xFFBB86FC : 0xFFCCCCCC;
            g.drawString(this.font, cat.name, 42, y + 8, textColor);
            y += 30;
        }

        // Module panel
        List<Module> modules = ModuleManager.getByCategory(selected);
        int panelHeight = 40 + modules.size() * 30;

        g.fill(panelX, panelY, panelX + 300, panelY + panelHeight, 0xFF12121A);
        g.drawString(this.font, selected.name, panelX + 14, panelY + 12, 0xFFBB86FC);

        int modY = panelY + 38;
        for (Module mod : modules) {
            boolean hover = mouseX >= panelX + 12 && mouseX <= panelX + 288
                    && mouseY >= modY && mouseY <= modY + 26;

            int bg = mod.isEnabled() ? 0xFF2E1A4A : (hover ? 0xFF1E1E2A : 0xFF16161E);
            g.fill(panelX + 12, modY, panelX + 288, modY + 26, bg);

            // Status indicator
            int indicatorColor = mod.isEnabled() ? 0xFFBB86FC : 0xFF444455;
            g.fill(panelX + 18, modY + 8, panelX + 24, modY + 18, indicatorColor);

            int textColor = mod.isEnabled() ? 0xFFBB86FC : 0xFFCCCCCC;
            g.drawString(this.font, mod.getName(), panelX + 32, modY + 9, textColor);

            modY += 30;
        }

        super.render(g, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return super.mouseClicked(mouseX, mouseY, button);

        // Category selection
        int y = 85;
        for (Category cat : Category.values()) {
            if (mouseX >= 30 && mouseX <= 130 && mouseY >= y && mouseY <= y + 24) {
                selected = cat;
                return true;
            }
            y += 30;
        }

        // Module toggle
        List<Module> modules = ModuleManager.getByCategory(selected);
        int modY = panelY + 38;
        for (Module mod : modules) {
            if (mouseX >= panelX + 12 && mouseX <= panelX + 288
                    && mouseY >= modY && mouseY <= modY + 26) {
                mod.toggle();
                return true;
            }
            modY += 30;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

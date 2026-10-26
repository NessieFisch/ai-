package com.yourname.pvpclient.hud;

import com.yourname.pvpclient.music.NowPlaying;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;

public class TopBarHud {

    public static void init() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath("pvpclient", "topbar"),
                TopBarHud::render
        );
    }

    private static void render(GuiGraphics g, net.minecraft.client.DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        int width = mc.getWindow().getGuiScaledWidth();

        // Top bar background
        g.fill(0, 0, width, 26, 0xE0101018);

        // Left: name + FPS
        String left = mc.player.getName().getString() + "  •  " + mc.getFps() + " FPS";
        g.drawString(mc.font, left, 8, 8, 0xFFE0E0E0);

        // Center: mode + time
        String mode = mc.hasSingleplayerServer() ? "Singleplayer" : "Multiplayer";
        String time = java.time.LocalTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
        String center = mode + "  •  " + time;
        int centerWidth = mc.font.width(center);
        g.drawString(mc.font, center, (width - centerWidth) / 2, 8, 0xFFBBBBBB);

        // Speed
        double dx = mc.player.getDeltaMovement().x;
        double dz = mc.player.getDeltaMovement().z;
        double speed = Math.sqrt(dx * dx + dz * dz) * 20.0;
        String speedStr = String.format("%.1f b/s", speed);
        g.drawString(mc.font, speedStr, width - 210, 8, 0xFFAAAAAA);

        // Music widget
        NowPlaying.render(g, width - 195, 3);
    }
}

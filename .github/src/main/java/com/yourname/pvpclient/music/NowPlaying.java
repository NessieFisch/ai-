package com.yourname.pvpclient.music;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class NowPlaying {

    public static String title = "Nothing playing";
    public static String artist = "";
    public static boolean playing = false;
    public static float progress = 0.0f; // 0.0 - 1.0

    public static void render(GuiGraphics g, int x, int y) {
        Minecraft mc = Minecraft.getInstance();

        // Card background
        g.fill(x, y, x + 188, y + 20, 0xFF1A1A24);

        // Text
        String text;
        if (playing) {
            text = title;
            if (!artist.isEmpty()) {
                text = title + " - " + artist;
            }
        } else {
            text = "No media";
        }

        if (text.length() > 26) {
            text = text.substring(0, 23) + "...";
        }

        g.drawString(mc.font, text, x + 6, y + 5, 0xFFFFFFFF);

        // Progress bar
        g.fill(x + 6, y + 16, x + 182, y + 18, 0xFF333344);
        int barWidth = (int) (176 * Mth.clamp(progress, 0f, 1f));
        g.fill(x + 6, y + 16, x + 6 + barWidth, y + 18, 0xFFBB86FC);
    }

    /**
     * Call this from a background thread or tick to update the displayed track.
     * For real Spotify / YouTube Music you will connect this to System Media Session
     * or the Spotify Web API later.
     */
    public static void update(String newTitle, String newArtist, boolean isPlaying, float prog) {
        title = newTitle != null ? newTitle : "Unknown";
        artist = newArtist != null ? newArtist : "";
        playing = isPlaying;
        progress = Mth.clamp(prog, 0f, 1f);
    }
}

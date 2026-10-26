package com.yourname.pvpclient;

import com.yourname.pvpclient.gui.ClickGuiScreen;
import com.yourname.pvpclient.hud.TopBarHud;
import com.yourname.pvpclient.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class PvPClient implements ClientModInitializer {

    public static final String MOD_ID = "pvpclient";
    public static KeyMapping OPEN_GUI;

    @Override
    public void onInitializeClient() {
        // Right Shift opens the GUI
        OPEN_GUI = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.pvpclient.open_gui",
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.pvpclient"
        ));

        ModuleManager.init();
        TopBarHud.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_GUI.consumeClick()) {
                if (client.screen == null) {
                    client.setScreen(new ClickGuiScreen());
                }
            }

            // Tick all enabled modules
            ModuleManager.MODULES.forEach(m -> {
                if (m.isEnabled()) m.onTick();
            });
        });

        System.out.println("[PvP Client] Loaded successfully on 26.2");
    }
}

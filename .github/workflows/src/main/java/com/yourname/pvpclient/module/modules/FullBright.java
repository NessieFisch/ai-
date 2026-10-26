package com.yourname.pvpclient.module.modules;

import com.yourname.pvpclient.module.Category;
import com.yourname.pvpclient.module.Module;
import net.minecraft.client.Minecraft;

public class FullBright extends Module {
    private double oldGamma = 1.0;

    public FullBright() {
        super("FullBright", "Makes everything fully bright", Category.MISC);
    }

    @Override
    protected void onEnable() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options != null) {
            oldGamma = mc.options.gamma().get();
            mc.options.gamma().set(16.0);
        }
    }

    @Override
    protected void onDisable() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options != null) {
            mc.options.gamma().set(oldGamma);
        }
    }
}

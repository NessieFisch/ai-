package com.yourname.pvpclient.module;

import com.yourname.pvpclient.module.modules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {

    public static final List<Module> MODULES = new ArrayList<>();

    public static void init() {
        // Visual / QoL
        MODULES.add(new NoHurtCam());
        MODULES.add(new LowFire());
        MODULES.add(new SmallTotem());
        MODULES.add(new NoDeathAnimation());

        // HUD
        MODULES.add(new ArmorHud());
        MODULES.add(new EffectTimer());

        // Misc
        MODULES.add(new FullBright());

        System.out.println("[PvP Client] Registered " + MODULES.size() + " modules");
    }

    public static List<Module> getByCategory(Category category) {
        return MODULES.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    public static Module getByName(String name) {
        return MODULES.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

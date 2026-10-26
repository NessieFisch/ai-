package com.yourname.pvpclient.module;

public enum Category {
    COMBAT("Combat"),
    VISUALS("Visuals"),
    HUD("HUD"),
    MISC("Misc");

    public final String name;

    Category(String name) {
        this.name = name;
    }
}

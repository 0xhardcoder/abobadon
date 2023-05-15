package com.abobadon.addon;

import com.abobadon.addon.commands.CommandAbobadon;
import com.abobadon.addon.hud.HudAbobadon;
import com.abobadon.addon.modules.ModuleAbobadon;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Abobadon");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Meteor Addon Abobadon");

        // Modules
        Modules.get().add(new Moduleabobadon());

    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.abobadon.addon";
    }
}

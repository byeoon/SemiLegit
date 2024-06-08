/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.render;

import meteordevelopment.meteorclient.gui.GuiThemes;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.hud.screens.AddHudElementScreen;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;

import static meteordevelopment.meteorclient.systems.hud.screens.HudEditorScreen.lastMouseX;
import static meteordevelopment.meteorclient.systems.hud.screens.HudEditorScreen.lastMouseY;


public class ShowModules extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    // BIG:


    public ShowModules() {
        super(Categories.Render, "HUDModules", "if you dont know how tf to do it.");
    }

    @Override
    public void onActivate() {
       mc.setScreen(new AddHudElementScreen(GuiThemes.theme, lastMouseX, lastMouseY));
    }

}

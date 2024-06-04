/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules;

import meteordevelopment.meteorclient.addons.AddonManager;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import net.minecraft.item.Items;

public class Categories {
    public static final Category Combat = new Category("combat", Items.DIAMOND_SWORD.getDefaultStack());
    public static final Category Player = new Category("player", Items.PLAYER_HEAD.getDefaultStack());
    public static final Category Movement = new Category("movement", Items.SPLASH_POTION.getDefaultStack());
    public static final Category Render = new Category("render", Items.SPYGLASS.getDefaultStack());
    public static final Category World = new Category("world", Items.GRASS_BLOCK.getDefaultStack());
    public static final Category Misc = new Category("misc", Items.BLACK_BANNER.getDefaultStack());
    public static final Category Exploit = new Category("exploit", Items.LAVA_BUCKET.getDefaultStack());

    public static boolean REGISTERING;

    public static void init() {
        REGISTERING = true;

        // Meteor
        Modules.registerCategory(Combat);
        Modules.registerCategory(Player);
        Modules.registerCategory(Movement);
        Modules.registerCategory(Render);
        Modules.registerCategory(World);
        Modules.registerCategory(Misc);
        Modules.registerCategory(Exploit);

        // Addons
        AddonManager.ADDONS.forEach(MeteorAddon::onRegisterCategories);

        REGISTERING = false;
    }
}

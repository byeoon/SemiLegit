/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.player;

import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.systems.modules.movement.NoFall;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public class Damage extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<Integer> multiplier = sgGeneral.add(new IntSetting.Builder()
        .name("amount")
        .description("Amount of damage to take.")
        .defaultValue(1)
        .min(1)
        .sliderMin(1)
        .max(7)
        .build()
    );

    public Damage() {
        super(Categories.Player, "Damage", "Take damage.");
    }

    @Override
    public void onActivate() {
        if (mc.player.getAbilities().invulnerable) {

        }

        damagePlayer(multiplier.get());
    }

    private void damagePlayer(int amount) {
        boolean noFall = Modules.get().isActive(NoFall.class);
        if (noFall) Modules.get().get(NoFall.class).toggle();

        boolean antiHunger = Modules.get().isActive(AntiHunger.class);
        if (antiHunger) Modules.get().get(AntiHunger.class).toggle();

        Vec3d pos = mc.player.getPos();

        for(int i = 0; i < 80; i++) {
            sendPositionPacket(pos.x, pos.y + amount + 2.1, pos.z, false);
            sendPositionPacket(pos.x, pos.y + 0.05, pos.z, false);
        }

        sendPositionPacket(pos.x, pos.y, pos.z, true);

        if (noFall) Modules.get().get(NoFall.class).toggle();
        if (antiHunger) Modules.get().get(AntiHunger.class).toggle();
    }

    private void sendPositionPacket(double x, double y, double z, boolean onGround) {
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, onGround));
    }

}

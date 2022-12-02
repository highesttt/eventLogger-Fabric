package com.highest.eventLogger.events;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Date;

import org.jetbrains.annotations.Nullable;

import com.highest.eventLogger.fileop.updateFile;

public class playerHurtEvent implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!world.isClient() && !player.isSpectator()) {
            String message = "[" + new Date().toString() + "] " + player.getName().getString() + " has damaged a " + entity.getName().getString() + ".";
            updateFile.update(player.getId(), message, "damage");
        }

        return ActionResult.PASS;
    }
}

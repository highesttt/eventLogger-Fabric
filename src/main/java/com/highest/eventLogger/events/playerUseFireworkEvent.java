package com.highest.eventLogger.events;

import java.util.Date;

import com.highest.eventLogger.fileop.updateFile;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class playerUseFireworkEvent implements UseItemCallback {
    long eventcalled = 0;
    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        if (!(eventcalled == 0) && (eventcalled + 100 > System.currentTimeMillis())) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }
        eventcalled = System.currentTimeMillis();

        ItemStack item = player.getStackInHand(hand);
        if (item.getName().getString().contains("Rocket")) {
            if (player.isFallFlying() && player.getInventory().getArmorStack(2).getName().getString().contains("Elytra")) {
                String message = "[" + new Date().toString() + "] " + player.getName().getString() + " used a rocket while flying.";
                updateFile.update(player.getId(), message, "rockets");
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));
    }
}

package com.highest.eventLogger.mixin;

import com.highest.eventLogger.fileop.updateFile;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Date;

@Mixin(Item.class)
public class playerEatMixin {

    @Inject(method = "finishUsing", at = @At("HEAD"))
    public void onClientEat(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {

        if (!RenderSystem.isOnRenderThread()) return;

        PlayerEntity player = user instanceof PlayerEntity ? (PlayerEntity) user : null;

        if (player == null) return;
        
        String message = "[" + new Date().toString() + "] " + player.getName().getString() + " ate " + stack.getName().getString() + ".";
        
        updateFile.update(player.getId(), message, "eat");
    }
}


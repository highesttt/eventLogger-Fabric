package com.highest.eventLogger.mixin;

import com.highest.eventLogger.fileop.updateFile;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Date;

/**
 * Mixin for detecting death of client player. Credit: https://github.com/gliscowo/deathlog/blob/d8f7d354d9c51a72dd9aec33d2cf85e234c499a2/src/main/java/com/glisco/deathlog/mixin/ClientPlayNetworkHandlerMixin.java
 */
@Mixin(ClientPlayNetworkHandler.class)
public class playerDeathMixin {

    @Inject(method = "onDeathMessage", at = @At("HEAD"))
    public void onClientDeath(DeathMessageS2CPacket packet, CallbackInfo ci) throws IOException {
        if (!RenderSystem.isOnRenderThread()) return;

        PlayerEntity player = MinecraftClient.getInstance().player;
        String message = "[" + new Date().toString() + "] " + player.getName().asString() + " died.";

        updateFile.updateFile(packet.getEntityId(), message);
    }
}

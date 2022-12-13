package com.highest.eventLogger.mixin;

import com.highest.eventLogger.fileop.updateFile;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Date;

@Mixin(ClientPlayNetworkHandler.class)
public class playerJoinMixin {

    @Inject(method = "onGameJoin", at = @At("TAIL"))
    public void onJoin(GameJoinS2CPacket packet, CallbackInfo ci) throws IOException {

        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        String message = "[" + new Date().toString() + "] " + player.getName().getString() + " has connected.";

        updateFile.update(packet.playerEntityId(), message, "connect");
    }
}

package com.highest.eventLogger.mixin;

import com.highest.eventLogger.fileop.updateFile;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Date;

@Mixin(ClientConnection.class)
public class playerLeaveMixin {

    @Inject(method = "handleDisconnection", at = @At("HEAD"))
    public void onLeave(CallbackInfo ci) throws IOException {

        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        String message = "[" + new Date().toString() + "] " + player.getName().getString() + " has disconnected.";

        updateFile.update(player.getId(), message, "disconnect");
    }
}

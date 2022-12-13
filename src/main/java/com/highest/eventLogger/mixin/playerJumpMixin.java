package com.highest.eventLogger.mixin;

import com.highest.eventLogger.fileop.updateFile;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Date;

@Mixin(PlayerEntity.class)
public class playerJumpMixin {

    @Inject(method = "jump", at = @At("HEAD"))
    public void playerJump(CallbackInfo ci) throws IOException {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player == null) {
            return;
        }
        if (!RenderSystem.isOnRenderThread()) return;

        String message = "[" + new Date().toString() + "] " + player.getName().getString() + " has jumped.";

        updateFile.update(player.getId(), message, "jump");
    }
}

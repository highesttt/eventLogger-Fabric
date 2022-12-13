package com.highest.eventLogger.mixin;

import com.highest.eventLogger.fileop.updateFile;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Date;

@Mixin(PlayerEntity.class)
public class playerDamagedMixin {

    @Inject(method = "applyDamage", at = @At("HEAD"))
    public void playerDamaged(net.minecraft.entity.damage.DamageSource source, float amount, CallbackInfo ci) throws IOException {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player == null) {
            return;
        }
        String attacker;
        if (source.getAttacker() == null) {
            attacker = source.getName();
        } else {
            attacker = source.getAttacker().getName().getString();
        }
        String message = "[" + new Date().toString() + "] " + player.getName().getString() + " has been damaged by " + attacker + ".";

        updateFile.update(player.getId(), message, "damaged");
    }
}

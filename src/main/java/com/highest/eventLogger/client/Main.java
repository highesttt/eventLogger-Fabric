package com.highest.eventLogger.client;

import com.highest.eventLogger.events.playerUseFireworkEvent;
import com.highest.eventLogger.events.playerHurtEvent;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class Main implements ModInitializer {
    public static final String MOD_ID = "eventLogger";

    @Override
    public void onInitialize() {
		AttackEntityCallback.EVENT.register(new playerHurtEvent());
		UseItemCallback.EVENT.register(new playerUseFireworkEvent());
    }
}
package com.highest.eventLogger.client;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static final String MOD_ID = "eventLogger";

    @Override
    public void onInitialize() {
        System.out.println("eventLogger v1.0 by Highest loaded.");
    }
}
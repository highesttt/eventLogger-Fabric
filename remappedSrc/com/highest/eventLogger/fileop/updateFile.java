package com.highest.eventLogger.fileop;

import java.io.File;

import net.minecraft.client.MinecraftClient;

public class updateFile {
    public static void updateFile(int entityid, String content) {
        if (entityid == MinecraftClient.getInstance().player.getId()) {
            File file = new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\examplemod\\death.txt");
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                java.io.FileWriter writer = new java.io.FileWriter(file);
                writer.write(content);
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

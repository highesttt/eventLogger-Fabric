package com.highest.eventLogger.fileop;

import java.io.File;
import java.io.FileWriter;

import net.minecraft.client.MinecraftClient;

public class updateFile {
    public static void updateFile(int entityid, String content) {
        if (entityid == MinecraftClient.getInstance().player.getId()) {
            File file = new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\eventLogger\\events.txt");
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                // add a new line to the file
                FileWriter writer = new FileWriter(file, true);
                writer.write(content + "\n");
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.highest.eventLogger.fileop;

import java.io.File;
import java.io.FileWriter;

import net.minecraft.client.MinecraftClient;

public class updateFile {
    public static void update(int entityid, String content, String filename) {
        if (entityid == MinecraftClient.getInstance().player.getId()) {
            File file = new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\eventLogger\\" + filename + ".txt");
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

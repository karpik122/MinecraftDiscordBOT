package main.java.MinecraftDiscordBOT;

import java.io.File;
import java.util.TimerTask;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class AlertDisplayer extends TimerTask {
    public AlertDisplayer() {
    }

    public void run() {
        File f = new File("plugins/MinecraftDiscordBOT/alerts.yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
        if (file.getBoolean("activealert")) {
            Bukkit.getServer().getOnlinePlayers().forEach((pl) -> {
                pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + file.getString("alert")));
            });
        }

    }
}

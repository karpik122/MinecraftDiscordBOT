package main.java.minecraftBOT;

import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.io.File;

public class Alert implements CommandExecutor {
    @SneakyThrows
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        File f = new File("plugins/MinecraftDiscortBOT/alerts.yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
        if(!(sender instanceof Player)) {
            String content = "";
            for(int i = 0;i<args.length; i++) {
                content = content + " " + args[i];
            }
            file.set("activealert", true);
            file.set("alert", content);
            file.save(f);
        }
        return false;
    }
}

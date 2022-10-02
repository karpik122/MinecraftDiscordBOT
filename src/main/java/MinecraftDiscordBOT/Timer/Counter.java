package main.java.MinecraftDiscordBOT.Timer;

import java.io.File;
import java.util.List;
import java.util.TimerTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Counter extends TimerTask {
    public Counter() {
    }

    public void run() {
        try {
            System.out.println(ChatColor.RED + "Zapisano czas gry");
            File f = new File("plugins/MinecraftDiscordBOT/playtime.yml");
            YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
            List<Player> players = (List)Bukkit.getOnlinePlayers();

            for(int i = 0; i < players.size(); ++i) {
                if (file.get(((Player)players.get(i)).getName()) == null) {
                    file.set(((Player)players.get(i)).getName(), 0);
                }

                file.save(f);
                file.set(((Player)players.get(i)).getName(), file.getInt(((Player)players.get(i)).getName()) + 1);
                file.save(f);
            }

            file.save(f);
        } catch (Throwable var5) {
            throw var5;
        }
    }
}

package main.java.minecraftBOT.Timer;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;

import java.io.File;
import java.util.List;
import java.util.TimerTask;

public class Counter extends TimerTask {
    @SneakyThrows
    @Override
    public void run() {
        System.out.println(ChatColor.RED+"Zapisano czas gry");
        File f = new File("plugins/MinecraftDiscortBOT/playtime.yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(f);

        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();


        for(int i = 0; i<players.size(); i++) {
            if(file.get(players.get(i).getName())==null) file.set(players.get(i).getName(), 0); file.save(f);

            file.set(players.get(i).getName(), file.getInt(players.get(i).getName())+1);

            file.save(f);

        }

        file.save(f);
    }


}
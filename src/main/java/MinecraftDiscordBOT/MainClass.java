package main.java.MinecraftDiscordBOT;

import java.io.File;
import java.util.Timer;
import javax.security.auth.login.LoginException;

import com.mysql.fabric.xmlrpc.base.Data;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import main.java.MinecraftDiscordBOT.Discord.StatusUpdater;
import main.java.MinecraftDiscordBOT.Discord.User;
import main.java.MinecraftDiscordBOT.Logi.CommandLogger;
import main.java.MinecraftDiscordBOT.Timer.Counter;
import main.java.MinecraftDiscordBOT.Timer.DiscordAllTime;
import main.java.MinecraftDiscordBOT.Timer.DiscordCheck;

public final class MainClass extends JavaPlugin implements Listener {
    public static JDA jda;

    public MainClass() {
    }


    public void onEnable() {
        super.onEnable();

        saveDefaultConfig();

        String discordToken = getConfig().getString("TOKEN");
        if (discordToken == null) {
            getServer().getPluginManager().disablePlugin(this);
            getLogger().severe("proszę podać TOKEN w pliku config.yml");
            return;
        }

        this.jda = JDABuilder.createDefault(discordToken)
                .build();


        try {
            System.out.println(ChatColor.RED + "MinecraftDiscordBOT - Ładowanie");
            System.out.println(ChatColor.GREEN + "MinecraftDiscordBOT - Zalogowano jako " + jda.getSelfUser().getName() + "#" + jda.getSelfUser().getDiscriminator() + " (" + jda.getSelfUser().getId() + ")");
            this.getServer().getPluginManager().registerEvents(this, this);
            this.getServer().getPluginManager().registerEvents(new CommandLogger(), this);
            this.getServer().getPluginManager().registerEvents(new AutoMessages(), this);
            this.getCommand("report").setExecutor(new Reports());
            this.getCommand("alert").setExecutor(new Alert());
            Timer timer = new Timer();
            timer.schedule(new StatusUpdater(), 0L, 30000L);
            timer.schedule(new AutoMessages(), 0L, 600000L);
            timer.schedule(new Counter(), 0L, 60000L);
            timer.schedule(new AlertDisplayer(), 0L, 1000L);
            File f = new File("plugins/MinecraftDiscordBOT/alerts.yml");
            YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
            file.set("activealert", false);
            file.save(f);
        } catch (Throwable var4) {
            throw var4;
        }
    }

    public void onDisable() {
    }



    public static JDA getJda() {
        return jda;
    }
}
